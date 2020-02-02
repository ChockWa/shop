package org.chock.shop.service;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.var;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.dto.GroupLoginResp;
import org.chock.shop.entity.GroupCard;
import org.chock.shop.entity.GroupUser;
import org.chock.shop.exception.BizException;
import org.chock.shop.mapper.GroupCardMapper;
import org.chock.shop.mapper.GroupUserMapper;
import org.chock.shop.util.JwtUtils;
import org.chock.shop.util.MD5Utils;
import org.chock.shop.util.RedisUtils;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class GroupUserService {

    @Autowired
    private GroupUserMapper groupUserMapper;
    @Autowired
    private GroupCardMapper groupCardMapper;
    @Autowired
    private RedisUtils redisUtils;

    public GroupLoginResp register(String userName, String password, String email){
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            throw new BizException(9999, "用户名和密码不能为空!");
        }
        List<GroupUser> exists = groupUserMapper.selectList(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getUserName, userName)
        .or().eq(StringUtils.isNotBlank(email), GroupUser::getEmail, email));
        if(exists.size() > 0){
            throw new BizException(9999, "用户名或邮箱已存在!");
        }
        String salt = UUIDUtils.getUuid();
        String hexPassword = MD5Utils.md5(salt + password + salt);
        GroupUser user = new GroupUser();
        user.setCreateTime(new Date());
        String uid = UUIDUtils.getUuid();
        user.setUid(uid);
        user.setUserName(userName);
        user.setPassword(hexPassword);
        user.setEmail(email);
        user.setSalt(salt);
        groupUserMapper.insert(user);

        String token = JwtUtils.createToken(uid, userName);
        redisUtils.set(token, user);
        return new GroupLoginResp(token, userName, null);
    }

    public GroupLoginResp login(String userName, String password){
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            throw new BizException(9999, "用户名和密码不能为空!");
        }
        List<GroupUser> exists = groupUserMapper.selectList(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getUserName, userName));
        if(exists.size() < 1){
            throw new BizException(9999, "用户不存在!");
        }

        GroupUser user = exists.get(0);
        String hexPassword = MD5Utils.md5(user.getSalt() + password + user.getSalt());
        if(!hexPassword.equals(user.getPassword())){
            throw new BizException(9999, "密码错误!");
        }

        String token = JwtUtils.createToken(user.getUid(), userName);
        redisUtils.set(token, user);
        return new GroupLoginResp(token, userName, user.getVipEndTime());
    }

    @Transactional(rollbackFor = Exception.class)
    public GroupUser chargeVip(String cardNo, String token){
        if(StringUtils.isBlank(cardNo)){
            throw new BizException(9999, "卡号不能为空!");
        }
//        GroupCard card = groupCardMapper.selectById(cardNo);
//        if(card == null || StringUtils.isNotBlank(card.getUid())){
//            throw new IllegalArgumentException(9999, "卡号不存在或已使用");
//        }
        GroupUser user = (GroupUser) redisUtils.get(token);
//        card.setUid(user.getUid());
//        card.setUseTime(new Date());
//        card.setStatus(0);
//        groupCardMapper.updateById(card);

        user.setVipEndTime(DateUtil.offsetMonth(new Date(), 1)); // 一个月
        groupUserMapper.updateById(user);
        return user;
    }

    public void checkExpire(String userName){
        if(StringUtils.isBlank(userName)){
            throw new BizException(9999, "用户名不能为空!");
        }

        List<GroupUser> users = groupUserMapper.selectList(Wrappers.<GroupUser>lambdaQuery().eq(GroupUser::getUserName, userName));
        if(users.size() < 1){
            throw new BizException(9999, "用户不存在!");
        }
        var user = users.get(0);

        if(user.getVipEndTime() == null || new Date().after(user.getVipEndTime())){
            throw new BizException(9999, "非月会员或会员已过期,请登录网站续费!");
        }
    }
}

package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.BizException;
import org.chock.shop.dto.Code2SessionResp;
import org.chock.shop.dto.LoginMgmtResp;
import org.chock.shop.dto.WxUserInfo;
import org.chock.shop.entity.User;
import org.chock.shop.mapper.UserMapper;
import org.chock.shop.util.MD5Utils;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AuthService authService;

    public String wxLogin(WxUserInfo wxUserInfo, String code){
        // 授权获取openId
        Code2SessionResp resp = authService.code2Session(code);
        if(resp == null || StringUtils.isBlank(resp.getOpenId())){
            throw BizException.AUTH_FAIL_ERROR;
        }

        User user = register(resp.getOpenId(), wxUserInfo);
        return authService.generateToken(user);
    }

    private User register(String openId, WxUserInfo wxUserInfo){
        User exist = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getOpenId, openId));
        // 若已存在的用户则只做更新
        if(exist != null){
            exist.setAvatarUrl(wxUserInfo.getAvatarUrl());
            exist.setName(wxUserInfo.getNickName());
            exist.setGender(wxUserInfo.getGender());
            userMapper.updateById(exist);
            return exist;
        }
        exist = new User();
        exist.setUid(UUIDUtils.getUuid());
        exist.setOpenId(openId);
        exist.setAvatarUrl(wxUserInfo.getAvatarUrl());
        exist.setName(wxUserInfo.getNickName());
        exist.setGender(wxUserInfo.getGender());
        exist.setCreateTime(new Date());
        exist.setStatus(1);
        userMapper.insert(exist);
        return exist;
    }

    public LoginMgmtResp loginMgmt(String email, String password){
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail, email));
        String hexPassword = MD5Utils.md5(user.getSalt() + password + user.getSalt());
        if(!hexPassword.equals(user.getPassword())){
            throw new IllegalArgumentException("密码错误");
        }

        String token = authService.generateToken(user);
        return new LoginMgmtResp(token, user.getName());
    }
}

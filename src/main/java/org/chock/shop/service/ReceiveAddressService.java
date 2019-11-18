package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.chock.shop.dto.UserInfo;
import org.chock.shop.entity.ReceiveAddress;
import org.chock.shop.mapper.ReceiveAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/15 09:32
 * @description:
 */
@Service
public class ReceiveAddressService {

    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;

    public void add(ReceiveAddress receiveAddress){
        receiveAddress.setUid(UserInfo.get().getUid());
        receiveAddressMapper.insert(receiveAddress);
    }

    public void delete(String addressId){
        receiveAddressMapper.deleteById(addressId);
    }

    public void update(ReceiveAddress receiveAddress){
        receiveAddressMapper.updateById(receiveAddress);
    }

    public List<ReceiveAddress> list(){
        return receiveAddressMapper.selectList(Wrappers.<ReceiveAddress>lambdaQuery().eq(ReceiveAddress::getUid, UserInfo.get().getUid()));
    }
}

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
        if(receiveAddress.getIsDefault()){
            setAllAddressNotDefault();
        }
        receiveAddress.setUid(UserInfo.get().getUid());
        receiveAddressMapper.insert(receiveAddress);
    }

    private void setAllAddressNotDefault(){
        receiveAddressMapper.update(null, Wrappers.<ReceiveAddress>lambdaUpdate().set(ReceiveAddress::getIsDefault, false).eq(ReceiveAddress::getUid, UserInfo.get().getUid()));
    }

    public ReceiveAddress getById(String addressId){
        return receiveAddressMapper.selectById(addressId);
    }

    public void delete(String addressId){
        receiveAddressMapper.deleteById(addressId);
    }

    public void update(ReceiveAddress receiveAddress){
        if(receiveAddress.getIsDefault()){
            setAllAddressNotDefault();
        }
        receiveAddressMapper.updateById(receiveAddress);
    }

    public List<ReceiveAddress> list(){
        return receiveAddressMapper.selectList(Wrappers.<ReceiveAddress>lambdaQuery().eq(ReceiveAddress::getUid, UserInfo.get().getUid()));
    }

    public void setDefault(String addressId, Boolean isDefault){
        if(isDefault){
            setAllAddressNotDefault();
        }
        receiveAddressMapper.update(null, Wrappers.<ReceiveAddress>lambdaUpdate().set(ReceiveAddress::getIsDefault, isDefault).eq(ReceiveAddress::getId, addressId));
    }
}

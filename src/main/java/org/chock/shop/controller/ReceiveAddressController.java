package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.entity.ReceiveAddress;
import org.chock.shop.service.ReceiveAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/21 17:08
 * @description:
 */
@RestController
@RequestMapping("/receive-address")
public class ReceiveAddressController {

    @Autowired
    private ReceiveAddressService receiveAddressService;

    /**
     * 以下為用戶端接口
     * -------------------------------------------------------------------------------------------------
     * */

    @GetMapping("/list")
    public Result list(){
        return Result.SUCCESS().setData("list", receiveAddressService.list());
    }

    @PostMapping("/save")
    public Result save(@RequestBody ReceiveAddress receiveAddress) {
        receiveAddressService.add(receiveAddress);
        return Result.SUCCESS();
    }

//    @PostMapping("/update")
//    public Result update(@RequestBody ReceiveAddress receiveAddress){
//        receiveAddressService.update(receiveAddress);
//        return Result.SUCCESS();
//    }

    @GetMapping("/del")
    public Result delete(String addressId){
        receiveAddressService.delete(addressId);
        return Result.SUCCESS().setData("list", receiveAddressService.list());
    }

    @GetMapping("/getById")
    public Result getById(String addressId){
        return Result.SUCCESS().setData(receiveAddressService.getById(addressId));
    }

    @GetMapping("/setDefault")
    public Result setDefault(String addressId, Boolean isDefault){
        receiveAddressService.setDefault(addressId, isDefault);
        return Result.SUCCESS().setData("list", receiveAddressService.list());
    }
}

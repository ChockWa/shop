package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/14 16:28
 * @description:
 */
@RestController
@RequestMapping("/shopcart")
public class ShopCartController {

    @Autowired
    private ShopCartService shopCartService;

    @GetMapping("list")
    public Result getShopCartList(){
        return Result.SUCCESS().setData("list", shopCartService.getShopCartDetailList(null));
    }

    @GetMapping("add-back-list")
    public Result addShopCartBackList(String goodsDetailId, Integer quantity){
        shopCartService.addShopCart(goodsDetailId, quantity);
        return Result.SUCCESS().setData("list", shopCartService.getShopCartDetailList(null));
    }

    @GetMapping("add")
    public Result addShopCart(String goodsDetailId, Integer quantity){
        shopCartService.addShopCart(goodsDetailId, quantity);
        return Result.SUCCESS();
    }

}

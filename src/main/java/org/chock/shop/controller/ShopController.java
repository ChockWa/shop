package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("shop")
public class ShopController {

    @GetMapping("info")
    public Result getShopInfo(){
        Map<String, Object> shopInfo = new HashMap<>();
        shopInfo.put("descs", Arrays.asList("打开拼多多APP，搜索店铺：饰货"));
        shopInfo.put("contacts", Arrays.asList("微信：hzh081215"));
        return Result.SUCCESS().setData("info", shopInfo);
    }
}

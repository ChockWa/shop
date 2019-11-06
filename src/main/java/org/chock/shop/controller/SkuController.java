package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.entity.Sku;
import org.chock.shop.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 17:15
 * @description:
 */
@RestController
@RequestMapping("/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

    @GetMapping("/list")
    public Result list(){
        return Result.SUCCESS().setData("list", skuService.listSku());
    }

    @PostMapping("/save")
    public Result save(@RequestBody List<Sku> skus){
        skuService.save(skus);
        return Result.SUCCESS();
    }
}

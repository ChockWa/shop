package org.chock.shop.controller;

import org.chock.shop.dto.GoodsInfo;
import org.chock.shop.dto.PageParam;
import org.chock.shop.dto.Result;
import org.chock.shop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/7 10:12
 * @description:
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @PostMapping("/save")
    public Result saveGoods(@RequestBody GoodsInfo goodsInfo){
        goodsService.saveGoods(goodsInfo);
        return Result.SUCCESS();
    }

    @GetMapping("/listPage")
    public Result listGoodsPage(PageParam pageParam){
        return Result.SUCCESS().setData(goodsService.listGoodsPage(pageParam));
    }

    @GetMapping("/del")
    public Result delete(String goodsId){
        goodsService.delete(goodsId);
        return Result.SUCCESS();
    }
}

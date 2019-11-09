package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.dto.UpdateGoodsDetailDto;
import org.chock.shop.entity.GoodsDetail;
import org.chock.shop.service.GoodsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/7 11:07
 * @description:
 */
@RestController
@RequestMapping("/goods-detail")
public class GoodsDetailController {
    @Autowired
    private GoodsDetailService goodsDetailService;
    @GetMapping("/list")
    public Result listGoodsDetail(String goodsId){
        return Result.SUCCESS().setData("list", goodsDetailService.listGoodsDetail(goodsId));
    }

    @PostMapping("/update")
    public Result update(@RequestBody GoodsDetail goodsDetail){
        goodsDetailService.update(goodsDetail);
        return Result.SUCCESS();
    }

    @PostMapping("/update-list")
    public Result update(@RequestBody UpdateGoodsDetailDto updateGoodsDetailDto){
        goodsDetailService.updateList(updateGoodsDetailDto.getGoodsDetails());
        return Result.SUCCESS();
    }
}

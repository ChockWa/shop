package org.chock.shop.controller;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.chock.shop.dto.Result;
import org.chock.shop.service.GuessLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/14 10:50
 * @description:
 */
@RestController
@RequestMapping("/guess-like")
public class GuessLikeController {
    @Autowired
    private GuessLikeService guessLikeService;

    @GetMapping("/list")
    public Result list(){
        return Result.SUCCESS().setData("list", guessLikeService.list());
    }

    @GetMapping("/add")
    public Result add(String goodsId){
        guessLikeService.add(goodsId);
        return Result.SUCCESS();
    }

    @GetMapping("/del")
    public Result del(String goodsId){
        guessLikeService.delete(goodsId);
        return Result.SUCCESS();
    }

    /**
     * 以下是用户端接口
     * -------------------------------------------------------------------------------------------------------
     */

    @GetMapping("/items")
    public Result items(){
        return Result.SUCCESS().setData("list", guessLikeService.getGuessLikeGoodsItems());
    }

}

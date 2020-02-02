package org.chock.shop.controller;

/**
 * @auther: zhuohuahe
 * @date: 2019/6/18 18:25
 * @description:
 */
public class BaseController {

    public void fallBack(){
        throw new RuntimeException("服务器繁忙，请稍后再试！");
    }
}

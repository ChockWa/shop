package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/13 10:13
 * @description:
 */
@RestController
@RequestMapping("/order-detail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/detail")
    public Result getOrderDetailInfo(String orderNo){
        return Result.SUCCESS().setData(orderDetailService.getOrderDetailInfo(orderNo));
    }
}

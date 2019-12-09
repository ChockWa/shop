package org.chock.shop.controller;

import org.chock.shop.dto.AddOrderDto;
import org.chock.shop.dto.PageParam;
import org.chock.shop.dto.Result;
import org.chock.shop.entity.Order;
import org.chock.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/13 10:10
 * @description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/listPage")
    public Result listOrderPage(PageParam pageParam){
        return Result.SUCCESS().setData("list", orderService.listOrdersPage(pageParam));
    }

    @GetMapping("/pay-confirm")
    public Result payConfirm(String orderNo,Integer orderStatus){
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setStatus(orderStatus);
        order.setPayTime(new Date());
        order.setUpdateTime(new Date());
        orderService.updateByOrderNo(order);
        return Result.SUCCESS();
    }

    @GetMapping("/send-confirm")
    public Result sendConfirm(String orderNo, String expressName, String expressNo){
        orderService.sendConfirm(orderNo, expressName, expressNo);
        return Result.SUCCESS();
    }


    /**
     * 以下为用户端接口
     * -----------------------------------------------------------------------------------------------------------------
     */

    /**
     * 确认下单
     * @param orderToken
     * @return
     */
    @GetMapping("/orderConfirmInfo")
    public Result orderConfirmInfo(String orderToken){
        return Result.SUCCESS().setData(orderService.orderConfirmInfo(orderToken));
    }

    /**
     * 生成下单token
     * @param shopCardIds
     * @return
     */
    @PostMapping("/genOrderToken")
    public Result generateOrderConfirmToken(String shopCardIds){
        return Result.SUCCESS().setData("token", orderService.generateOrderConfirmToken(Arrays.asList(shopCardIds.split(","))));
    }

    /**
     * 下单
     * @param addOrderDto
     * @return
     */
    @PostMapping("/order")
    public Result order(@RequestBody AddOrderDto addOrderDto){
        orderService.addOrder(addOrderDto);
        return Result.SUCCESS();
    }

    @GetMapping("/orders")
    public Result orders(Integer orderStatus, PageParam pageParam){
        return Result.SUCCESS().setData(orderService.getOrderListByStatusPage(orderStatus, pageParam));
    }
}

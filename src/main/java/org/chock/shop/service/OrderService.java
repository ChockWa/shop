package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.chock.shop.dto.OrderInfo;
import org.chock.shop.dto.PageParam;
import org.chock.shop.dto.PageResult;
import org.chock.shop.entity.Order;
import org.chock.shop.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/11 17:13
 * @description:
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public PageResult<OrderInfo> listOrdersPage(PageParam pageParam){
        Page<OrderInfo> page = new Page<>(pageParam.getPageIndex(), pageParam.getPageSize());
        orderMapper.listOrdersPage(page);

        PageResult<OrderInfo> result = new PageResult<>();
        result.setTotal(page.getTotal());
        result.setRecords(page.getRecords());
        return result;
    }

    public Order getByOrderNo(String orderNo){
        return orderMapper.selectOne(Wrappers.<Order>lambdaQuery().eq(Order::getOrderNo, orderNo));
    }

    public void updateByOrderNo(Order order){
        orderMapper.updateById(order);
    }
}

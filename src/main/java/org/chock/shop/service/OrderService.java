package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.constant.OrderStatus;
import org.chock.shop.dto.*;
import org.chock.shop.entity.GoodsDetail;
import org.chock.shop.entity.Order;
import org.chock.shop.entity.OrderDetail;
import org.chock.shop.entity.ShopCart;
import org.chock.shop.exception.BizException;
import org.chock.shop.mapper.GoodsDetailMapper;
import org.chock.shop.mapper.OrderDetailMapper;
import org.chock.shop.mapper.OrderMapper;
import org.chock.shop.mapper.ShopCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/11 17:13
 * @description:
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

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

    public void addOrder(AddOrderDto addOrderDto){
        if(addOrderDto == null){
            throw BizException.ORDER_DATA_NULL_ERROR;
        }
        if(StringUtils.isBlank(addOrderDto.getReceiveAddressId())){
            throw BizException.RECEIVE_ADDRESS_NULL_ERROR;
        }
        if(CollectionUtils.isEmpty(addOrderDto.getShopCartIds())){
            throw BizException.ORDER_GOODS_NULL_ERROR;
        }
        List<ShopCart> shopCarts = shopCartMapper.selectList(Wrappers.<ShopCart>lambdaQuery().in(ShopCart::getId, addOrderDto.getShopCartIds()));
        // 计算总价格
        int totalAmount = 0;
        Map<String, GoodsDetail> goodsDetailMap = new HashMap<>(shopCarts.size());
        for(ShopCart shopCart : shopCarts){
            GoodsDetail detail = goodsDetailMapper.selectById(shopCart.getGoodsDetailId());
            totalAmount += detail.getPrice() * shopCart.getQuantity();
            goodsDetailMap.put(shopCart.getGoodsDetailId(), detail);
        }
        if(addOrderDto.getTotalAmount().intValue() != totalAmount){
            throw BizException.AMOUNT_NOT_EQUAL_ERROR;
        }

        // 生成订单信息
        String orderNo = generateOrderNo();
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUid(UserInfo.get().getUid());
        order.setAddressId(addOrderDto.getReceiveAddressId());
        order.setCreateTime(new Date());
        order.setTotalAmount(totalAmount);
        order.setStatus(OrderStatus.UNPAY.getCode());
        orderMapper.insert(order);

        // 生成订单详情
        for(ShopCart shopCart : shopCarts){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setGoodsDetailId(shopCart.getGoodsDetailId());
            orderDetail.setOrderNo(orderNo);
            orderDetail.setQuantity(shopCart.getQuantity());
            orderDetail.setAmount(goodsDetailMap.get(shopCart.getGoodsDetailId()).getPrice() * shopCart.getQuantity());
            orderDetail.setCreateTime(new Date());
            orderDetailMapper.insert(orderDetail);
        }
    }

    private String generateOrderNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsssss");
        return sdf.format(new Date());
    }
}

package org.chock.shop.service;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.constant.OrderStatus;
import org.chock.shop.dto.*;
import org.chock.shop.entity.*;
import org.chock.shop.exception.BizException;
import org.chock.shop.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

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
    private ShopCartService shopCartService;
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ExpressService expressService;
    @Autowired
    private ReceiveAddressStaticMapper receiveAddressStaticMapper;
    @Autowired
    private ReceiveAddressMapper receiveAddressMapper;
    @Autowired
    private OrderDetailService orderDetailService;

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
        orderMapper.update(order, Wrappers.<Order>lambdaUpdate().eq(Order::getOrderNo, order.getOrderNo()));
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
        List<ShopCart> shopCarts = shopCartService.getListByShopCardIds(addOrderDto.getShopCartIds());
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
            orderDetail.setGoodsId(goodsDetailMap.get(shopCart.getGoodsDetailId()).getGoodsId());
            orderDetail.setCreateTime(new Date());
            orderDetailMapper.insert(orderDetail);
        }

        // 生成靜態地址
        ReceiveAddress receiveAddress = receiveAddressMapper.selectById(addOrderDto.getReceiveAddressId());
        receiveAddressStaticMapper.insert(BeanUtil.toBean(receiveAddress, ReceiveAddressStatic.class));
    }

    private String generateOrderNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsssss");
        return sdf.format(new Date());
    }

    @Transactional(rollbackFor = Exception.class)
    public void sendConfirm(String orderNo, String expressName, String expressNo){
        String expressId = expressService.addExpress(expressName, expressNo);
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setExpressId(expressId);
        order.setStatus(OrderStatus.UNRECEIVE.getCode());
        order.setUpdateTime(new Date());
        updateByOrderNo(order);
    }

    public List<GoodsDetailInfo> orderConfirm(List<String> shopCartIds){
        return shopCartService.getShopCartDetailList(shopCartIds);
    }

    public PageResult<OrderDetailInfo> getOrderListByStatusPage(Integer orderStatus, PageParam pageParam){
        Page<Order> page = new Page<>(pageParam.getPageIndex(), pageParam.getPageSize());
        orderMapper.selectPage(page, Wrappers.<Order>lambdaQuery().eq(Order::getUid, UserInfo.get().getUid())
                .eq(Objects.nonNull(orderStatus), Order::getStatus, orderStatus));
        List<OrderDetailInfo> list = new ArrayList<>(pageParam.getPageSize());
        for(int i = 0; i < page.getRecords().size(); i++){
            list.add(orderDetailService.getOrderDetailInfo(page.getRecords().get(i).getOrderNo()));
        }

        PageResult<OrderDetailInfo> result = new PageResult<>();
        result.setRecords(list);
        result.setTotal(page.getTotal());
        return result;
    }

}

package org.chock.shop.service;

import org.apache.commons.lang3.StringUtils;
import org.chock.shop.dto.GoodsDetailInfo;
import org.chock.shop.dto.OrderDetailInfo;
import org.chock.shop.entity.Order;
import org.chock.shop.entity.Sku;
import org.chock.shop.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/11 17:49
 * @description:
 */
@Service
public class OrderDetailService {
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ExpressMapper expressMapper;
    @Autowired
    private ReceiveAddressStaticMapper receiveAddressStaticMapper;
    @Autowired
    private SkuService skuService;

    public OrderDetailInfo getOrderDetailInfo(String orderNo){
        if(StringUtils.isBlank(orderNo)){
            return null;
        }
        OrderDetailInfo orderDetailInfo = new OrderDetailInfo();
        Order order = orderService.getByOrderNo(orderNo);
        orderDetailInfo.setExpress(expressMapper.selectById(order.getExpressId()));
        orderDetailInfo.setReceiveAddressStatic(receiveAddressStaticMapper.selectById(order.getAddressId()));
        List<GoodsDetailInfo> detailInfos =  goodsDetailMapper.getOrderGoodsDetailList(orderNo);
        detailInfos.forEach(e -> {
            String[] skuIds = e.getSkuIds().split(",");
            Map<String, String> goodsSkuMap = new HashMap<>(skuIds.length);
            for(String s : skuIds){
                Sku sku = skuService.getById(s);
                goodsSkuMap.put(sku.getName(), sku.getValue());
            }
            e.setGoodsSkuMap(goodsSkuMap);
        });
        orderDetailInfo.setGoodsList(detailInfos);
        return orderDetailInfo;
    }
}

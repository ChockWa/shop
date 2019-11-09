package org.chock.shop.service;

import org.chock.shop.dto.GoodsDetailDto;
import org.chock.shop.entity.GoodsDetail;
import org.chock.shop.entity.Sku;
import org.chock.shop.mapper.GoodsDetailMapper;
import org.chock.shop.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/7 11:01
 * @description:
 */
@Service
public class GoodsDetailService {
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;
    @Autowired
    private SkuMapper skuMapper;

    public List<GoodsDetailDto> listGoodsDetail(String goodsId){
        List<GoodsDetailDto> list = goodsDetailMapper.listGoodsDetail(goodsId);
        list.forEach(e -> {
            String[] skuIds = e.getGoodsSkuIds().split(",");
            Map<String,String> goodsSkuMap = new HashMap<>();
            for(String skuId : skuIds){
                Sku sku = skuMapper.selectById(skuId);
                goodsSkuMap.put(sku.getName(), sku.getValue());
            }
            e.setGoodsSkuMap(goodsSkuMap);
        });
        return list;
    }

    public void update(GoodsDetail goodsDetail){
        goodsDetailMapper.updateById(goodsDetail);
    }

    public void updateList(List<GoodsDetail> goodsDetails){
        goodsDetails.forEach(e -> {
            update(e);
        });
    }

}

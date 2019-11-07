package org.chock.shop.service;

import org.chock.shop.dto.GoodsDetailDto;
import org.chock.shop.entity.GoodsDetail;
import org.chock.shop.mapper.GoodsDetailMapper;
import org.chock.shop.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
            String[] skuIds = e.getSkuIds().split(",");
            List<String> skuNames = new ArrayList<>(skuIds.length);
            for(String skuId : skuIds){
                skuNames.add(skuMapper.selectById(skuId).getName());
            }
            e.setSkuNames(String.join(",", skuNames));
        });
        return goodsDetailMapper.listGoodsDetail(goodsId);
    }

    public void update(GoodsDetail goodsDetail){
        goodsDetailMapper.updateById(goodsDetail);
    }
}

package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.chock.shop.dto.GoodsDetailDto;
import org.chock.shop.dto.GoodsItemDetail;
import org.chock.shop.entity.GoodsDetail;
import org.chock.shop.entity.GoodsSku;
import org.chock.shop.entity.Sku;
import org.chock.shop.mapper.GoodsDetailMapper;
import org.chock.shop.mapper.GoodsSkuMapper;
import org.chock.shop.mapper.SkuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

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

    /**
     * 获取用户端商品详情
     * @param goodsId
     * @return
     */
    public GoodsItemDetail getGoodsItemDetail(String goodsId){
        GoodsItemDetail itemDetail = goodsDetailMapper.getGoodsItemDetail(goodsId);
        itemDetail.setPrice(itemDetail.getGoodsDetailList().stream().min(Comparator.comparingInt(GoodsDetail::getPrice)).get().getPrice());
        List<GoodsSku> goodsSkus = goodsSkuMapper.selectList(Wrappers.<GoodsSku>lambdaQuery().eq(GoodsSku::getGoodsId, goodsId));
        List<Sku> skus = skuMapper.selectList(Wrappers.<Sku>lambdaQuery().in(Sku::getId, goodsSkus.stream().map(GoodsSku::getSkuId).collect(Collectors.toList())));
        Map<String, List<Sku>> skuListMap = skus.stream().collect(Collectors.groupingBy(Sku::getName));
        itemDetail.setGoodsSkuMap(skuListMap);
        return itemDetail;
    }
}

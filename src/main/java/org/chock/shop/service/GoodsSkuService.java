package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.chock.shop.entity.GoodsSku;
import org.chock.shop.mapper.GoodsSkuMapper;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 17:57
 * @description:
 */
@Service
public class GoodsSkuService {

    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    public String add(String goodsId, String skuId){
        GoodsSku goodsSku = new GoodsSku(UUIDUtils.getUuid(),goodsId,skuId);
        goodsSkuMapper.insert(goodsSku);
        return goodsSku.getId();
    }

    public void delete(String goodsSkuId){
        goodsSkuMapper.deleteById(goodsSkuId);
    }

    public void deleteByGoodsId(String goodsId){
        goodsSkuMapper.delete(Wrappers.<GoodsSku>lambdaQuery().eq(GoodsSku::getGoodsId, goodsId));
    }
}

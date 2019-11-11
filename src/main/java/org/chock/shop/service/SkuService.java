package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.chock.shop.entity.GoodsSku;
import org.chock.shop.entity.Sku;
import org.chock.shop.mapper.GoodsSkuMapper;
import org.chock.shop.mapper.SkuMapper;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 15:27
 * @description:
 */
@Service
public class SkuService {
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private GoodsSkuMapper goodsSkuMapper;

    public void add(List<Sku> skus){
        if(CollectionUtils.isEmpty(skus)){
            return;
        }
        String code = skus.get(0).getCode();
        if(skuMapper.selectList(Wrappers.<Sku>lambdaQuery().eq(Sku::getCode, code)).size() > 0){
            throw new RuntimeException("SKU编码已存在");
        }
        for(Sku sku : skus){
            sku.setId(UUIDUtils.getUuid());
            skuMapper.insert(sku);
        }
    }

    public Map<String,List<Sku>> listSku(){
        List<Sku> skus = skuMapper.selectList(null);
        if(CollectionUtils.isEmpty(skus)){
            return null;
        }
        return skus.stream().collect(Collectors.groupingBy(Sku::getName));
    }

    public void delete(String code){
        List<Sku> skus = skuMapper.selectList(Wrappers.<Sku>lambdaQuery().eq(Sku::getCode, code));
        List<String> skuIds = skus.stream().map(Sku::getId).collect(Collectors.toList());
        List<GoodsSku> exists = goodsSkuMapper.selectList(Wrappers.<GoodsSku>lambdaQuery().in(GoodsSku::getSkuId, skuIds));
        if(exists.size() > 0){
            throw new RuntimeException("该sku已在使用，不可删除");
        }
        skuMapper.delete(Wrappers.<Sku>lambdaQuery().eq(Sku::getCode, code));
    }

    public Sku getById(String skuId){
        return skuMapper.selectById(skuId);
    }
}

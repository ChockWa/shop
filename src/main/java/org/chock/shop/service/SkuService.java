package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.chock.shop.entity.Sku;
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

    public void save(List<Sku> skus){
        if(CollectionUtils.isEmpty(skus)){
            return;
        }
        String code = skus.get(0).getCode();
        skuMapper.delete(Wrappers.<Sku>lambdaQuery().eq(Sku::getCode, code));
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
}

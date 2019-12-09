package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Sets;
import org.chock.shop.dto.GoodsDetailInfo;
import org.chock.shop.dto.UserInfo;
import org.chock.shop.entity.ShopCart;
import org.chock.shop.entity.Sku;
import org.chock.shop.mapper.ShopCartMapper;
import org.chock.shop.mapper.SkuMapper;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/14 15:46
 * @description:
 */
@Service
public class ShopCartService {

    @Autowired
    private ShopCartMapper shopCartMapper;
    @Autowired
    private SkuMapper skuMapper;

    /**
     * 更新购物车商品数量
     * @param shopCartId
     */
    public synchronized void updateShopCartGoodsNumber(String shopCartId, Integer quantity){
        ShopCart updateEntity = new ShopCart();
        updateEntity.setId(shopCartId);
        updateEntity.setQuantity(quantity);
        updateEntity.setUpdateTime(new Date());
        shopCartMapper.updateById(updateEntity);
    }

    /**
     * 添加购物车
     * @param goodsDetailId
     * @param quantity
     */
    public synchronized void addShopCart(String goodsDetailId, Integer quantity) {
        ShopCart exist = shopCartMapper.selectOne(Wrappers.<ShopCart>lambdaQuery()
                .eq(ShopCart::getUid, UserInfo.get().getUid())
                .eq(ShopCart::getGoodsDetailId, goodsDetailId));
        if(exist == null){
            exist = new ShopCart();
            exist.setCreateTime(new Date());
            exist.setGoodsDetailId(goodsDetailId);
            exist.setQuantity(1);
            exist.setIsChecked(true);
            exist.setUid(UserInfo.get().getUid());
            exist.setId(UUIDUtils.getUuid());
            shopCartMapper.insert(exist);
        }else{
            exist.setQuantity(exist.getQuantity() + quantity);
            exist.setUpdateTime(new Date());
            shopCartMapper.updateById(exist);
        }
    }

    public List<ShopCart> getListByShopCardIds(List<String> shopCardIds){
        return shopCartMapper.selectList(Wrappers.<ShopCart>lambdaQuery().in(ShopCart::getId, shopCardIds));
    }

    /**
     * 获取购物车列表
     * @return
     */
    public List<GoodsDetailInfo> getShopCartDetailList(List<String> shopCartIds){
        List<GoodsDetailInfo> shopCartList = shopCartMapper.getShopCartList(UserInfo.get().getUid(), shopCartIds);
        Set<String> skuIdSet = new HashSet<>();
        shopCartList.forEach(e -> {
            skuIdSet.addAll(Arrays.asList(e.getSkuIds().split(",")));
        });
        List<Sku> skus = skuMapper.selectList(Wrappers.<Sku>lambdaQuery().in(Sku::getId, skuIdSet));
        shopCartList.forEach(e -> {
            e.setAmount(e.getPrice() * e.getQuantity());
            String[] skuIds = e.getSkuIds().split(",");
            Map<String,String> goodsSkuMap = new HashMap<>(skuIds.length);
            for(String skuId: skuIds){
                Sku sku = skus.stream().filter(f -> skuId.equals(f.getId())).findFirst().get();
                goodsSkuMap.put(sku.getName(), sku.getValue());
            }
            e.setGoodsSkuMap(goodsSkuMap);
        });
        return shopCartList;
    }

    public void delete(String[] shopCardIds){
        shopCartMapper.delete(Wrappers.<ShopCart>lambdaQuery().in(ShopCart::getId, shopCardIds));
    }

    public void check(String shopCartId, Boolean check){
        shopCartMapper.update(null, Wrappers.<ShopCart>lambdaUpdate().set(ShopCart::getIsChecked, check).eq(ShopCart::getId, shopCartId));
    }

    public void allCheck(Boolean check){
        shopCartMapper.update(null, Wrappers.<ShopCart>lambdaUpdate().set(ShopCart::getIsChecked, check).in(ShopCart::getUid, UserInfo.get().getUid()));
    }

}

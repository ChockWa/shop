package org.chock.shop.service;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.dto.GoodsInfo;
import org.chock.shop.entity.Goods;
import org.chock.shop.entity.Sku;
import org.chock.shop.mapper.GoodsMapper;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsSkuService goodsSkuService;

    public void saveGoods(GoodsInfo goodsInfo){
        // 保存商品信息
        Goods goods = new Goods();
        goods.setBrandId(goodsInfo.getBrandId());
        goods.setName(goodsInfo.getName());
        goods.setDescription(goodsInfo.getDescription());
        goods.setStatus(1);
        if(StringUtils.isBlank(goodsInfo.getGoodsId())){
            goods.setCreateTime(new Date());
            goods.setId(UUIDUtils.getUuid());
            goodsMapper.insert(goods);
        }else{
            goods.setUpdateTime(new Date());
            goodsMapper.updateById(goods);
        }
        // 获取所有sku组合保存信息
//        Map<String, List<Sku>> skuMap = goodsInfo.getSkuMap();
//        for(Map.Entry<String, List<Sku>> entry : skuMap.entrySet()){
//            for(){}
//        }
    }

    public static void main(String[] args) {
        List<Integer> i1 = Arrays.asList(1,2,3);
        List<Integer> i2 = Arrays.asList(4,5,6);
        List<List<Integer>> all = Arrays.asList(i1,i2);
        List<List<Integer>> initList = Arrays.asList(i1);
        List<List<Integer>> result = test(initList, all, 1);
        System.out.println(result.size());
        System.out.println(11111);
    }

    private static List<List<Integer>> test(List<List<Integer>> current, List<List<Integer>> all,Integer nextIndex){
        if(all.size() < nextIndex + 1){
            return current;
        }
        List<List<Integer>> lists = new ArrayList<>();
        for(List<Integer> is : current){
            List<Integer> tempList = new ArrayList<>(is);
            for(Integer j : all.get(nextIndex)){
                tempList.add(j);
            }
            lists.add(tempList);
        }
        return test(lists, all, nextIndex + 1);
    }
}

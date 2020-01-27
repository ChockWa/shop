package org.chock.shop.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.dto.*;
import org.chock.shop.entity.Goods;
import org.chock.shop.entity.GoodsDetail;
import org.chock.shop.entity.Sku;
import org.chock.shop.mapper.GoodsDetailMapper;
import org.chock.shop.mapper.GoodsMapper;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    @Value("${dns.https}")
    private String DNS_HTTPS;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;
    @Autowired
    private GoodsSkuService goodsSkuService;

    @Transactional(rollbackFor = Exception.class)
    public void saveGoods(GoodsInfo goodsInfo){
        // 保存商品信息
        Goods goods = new Goods();
        goods.setId(goodsInfo.getGoodsId());
        goods.setCategoryId(goodsInfo.getCategoryId());
        goods.setName(goodsInfo.getName());
        goods.setDescription(goodsInfo.getDescription());
        goods.setStatus(goodsInfo.isStatus()?1:0);
        goods.setCover(goodsInfo.getCover());
        goods.setImages(StringUtils.isBlank(goodsInfo.getImages())?" ":goodsInfo.getImages());
        if(StringUtils.isBlank(goodsInfo.getGoodsId())){
            goods.setCreateTime(new Date());
            goods.setId(UUIDUtils.getUuid());
            goodsMapper.insert(goods);
        }else{
            goods.setUpdateTime(new Date());
            goodsMapper.updateById(goods);
        }
        // 不能更新sku
//        if(StringUtils.isNoneBlank(goodsInfo.getGoodsId())){
//            return;
//        }

        // 获取所有sku组合保存信息
//        List<List<String>> allSkuIds = new ArrayList<>();
//        for(Map.Entry<String, List<Sku>> entry : goodsInfo.getSkuMap().entrySet()){
//            // 插入商品sku信息
//            for(Sku sku : entry.getValue()){
//                goodsSkuService.add(goods.getId(), sku.getId());
//            }
//            allSkuIds.add(entry.getValue().stream().map(Sku::getId).collect(Collectors.toList()));
//        }
//        List<String> firstSkuIds = allSkuIds.get(0);
//        List<List<String>> allSkuComposes = getAllSkuCompose(convert(firstSkuIds), allSkuIds, 1);
//
//        // 根据所有sku组合生成商品详情
//        for(List<String> skuIds : allSkuComposes){
//            GoodsDetail goodsDetail = new GoodsDetail();
//            goodsDetail.setId(UUIDUtils.getUuid());
//            goodsDetail.setGoodsId(goods.getId());
//            goodsDetail.setGoodsSkuIds(String.join(",", skuIds));
//            goodsDetail.setCreateTime(new Date());
//            goodsDetail.setStatus(1);
//            goodsDetailMapper.insert(goodsDetail);
//        }
    }

    private static List<List<String>> getAllSkuCompose(List<List<String>> current, List<List<String>> all,Integer nextIndex){
        if(all.size() < nextIndex + 1){
            return current;
        }
        List<List<String>> lists = new ArrayList<>();
        for(List<String> is : current){
            for(String j : all.get(nextIndex)){
                List<String> tempList = new ArrayList<>(is);
                tempList.add(j);
                lists.add(tempList);
            }
        }
        return getAllSkuCompose(lists, all, nextIndex + 1);
    }

    private static List<List<String>> convert(List<String> target){
        List<List<String>> lists = new ArrayList<>(target.size());
        for(String i : target){
            lists.add(Arrays.asList(i));
        }
        return lists;
    }

    public PageResult<GoodsDto> listGoodsPage(PageParam pageParam){
        Page<GoodsDto> page = new Page<>(pageParam.getPageIndex(),pageParam.getPageSize());
        goodsMapper.listGoodsPage(page, Collections.EMPTY_LIST);
        PageResult<GoodsDto> result = new PageResult<>();
        result.setRecords(page.getRecords());
        result.setTotal(page.getTotal());
        return result;
    }

//    @Transactional(rollbackFor = Exception.class)
    public void delete(String goodsId){
        // 先删除商品的sku信息
//        goodsSkuService.deleteByGoodsId(goodsId);
//        goodsDetailMapper.delete(Wrappers.<GoodsDetail>lambdaQuery().eq(GoodsDetail::getGoodsId, goodsId));
        goodsMapper.deleteById(goodsId);
    }

    public PageResult<GoodsInfoItem> getGoodsInfoItemsPage(GoodsInfoItemQuery query, PageParam pageParam){
        Page<GoodsInfoItem> page = new Page<>(pageParam.getPageIndex(), pageParam.getPageSize());
        goodsMapper.getGoodsInfoItemsPage(page, query);
//        page.getRecords().forEach(e -> {
//            e.setCover(DNS_HTTPS + e.getCover());
//        });

        PageResult<GoodsInfoItem> result = new PageResult<>();
        result.setRecords(page.getRecords());
        result.setTotal(page.getTotal());
        return result;
    }

    public Goods getById(String goodsId){
        return goodsMapper.selectById(goodsId);
    }
}

package org.chock.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chock.shop.dto.GoodsDto;
import org.chock.shop.dto.GoodsInfoItem;
import org.chock.shop.dto.GoodsInfoItemQuery;
import org.chock.shop.entity.Goods;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    IPage<GoodsDto> listGoodsPage(@Param("page") Page<GoodsDto> page, @Param("goodsIds") List<String> goodsIds);
    IPage<GoodsInfoItem> getGoodsInfoItemsPage(@Param("page") Page<GoodsInfoItem> page, @Param("query")GoodsInfoItemQuery query);
}

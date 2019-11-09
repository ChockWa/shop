package org.chock.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chock.shop.dto.GoodsDto;
import org.chock.shop.entity.Goods;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    IPage<GoodsDto> listGoodsPage(@Param("page") IPage<GoodsDto> page);
}

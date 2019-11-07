package org.chock.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.chock.shop.dto.GoodsDto;
import org.chock.shop.entity.Goods;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsDto> listGoodsPage(IPage<GoodsDto> page);
}

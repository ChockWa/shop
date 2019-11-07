package org.chock.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chock.shop.dto.GoodsDetailDto;
import org.chock.shop.entity.GoodsDetail;

import java.util.List;

@Mapper
public interface GoodsDetailMapper extends BaseMapper<GoodsDetail> {
    List<GoodsDetailDto> listGoodsDetail(String goodsId);
}

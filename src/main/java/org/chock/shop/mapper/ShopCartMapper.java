package org.chock.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chock.shop.dto.GoodsDetailInfo;
import org.chock.shop.entity.ShopCart;

import java.util.List;

@Mapper
public interface ShopCartMapper extends BaseMapper<ShopCart> {
    List<GoodsDetailInfo> getShopCartList(@Param("uid")String uid, @Param("shopCartIds")List<String> shopCartIds);
}

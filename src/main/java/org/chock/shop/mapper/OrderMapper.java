package org.chock.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.chock.shop.dto.OrderInfo;
import org.chock.shop.entity.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    IPage<OrderInfo> listOrdersPage(IPage<OrderInfo> page);
}

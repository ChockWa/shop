package org.chock.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chock.shop.entity.Category;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}

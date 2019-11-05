package org.chock.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.chock.shop.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

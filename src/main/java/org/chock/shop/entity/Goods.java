package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 09:38
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_goods")
public class Goods {
    @TableId(type = IdType.INPUT)
    private String id;
    private String brandId;
    private String name;
    private String description;
    private String images;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}

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
 * @date: 2019/11/6 09:40
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_goods_detail")
public class GoodsDetail {
    @TableId(type = IdType.INPUT)
    private String id;
    private String goodsId;
    private String goodsSkuIds;
    private String description;
    private Integer price;
    private Integer stock;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}

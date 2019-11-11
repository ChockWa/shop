package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

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
@TableName("s_shop_cart")
public class ShopCart {
    @TableId(type = IdType.INPUT)
    private String id;
    private String uid;
    private String goodsDetailId;
    private Integer quantity;
    private Date createTime;
    private Date updateTime;
}

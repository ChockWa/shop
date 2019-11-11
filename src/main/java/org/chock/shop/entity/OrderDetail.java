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
 * @date: 2019/11/11 13:51
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_order_detail")
public class OrderDetail {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String orderNo;
    private String goodsDetailId;
    private Integer quantity;
    private Integer amount;
    private Date createTime;
    private Date updateTime;
}

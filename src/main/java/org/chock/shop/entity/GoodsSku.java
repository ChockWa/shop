package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 17:47
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_goods_sku")
public class GoodsSku {
    @TableId(type = IdType.INPUT)
    private String id;
    private String goodsId;
    private String skuId;
}

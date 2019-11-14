package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/14 11:15
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfoItem {
    private String goodsId;
    private String goodsName;
    private Integer price;
    private String cover;
    private Integer saleCount;
}

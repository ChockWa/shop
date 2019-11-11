package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/11 17:24
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailInfo {
    private String name;
    private String cover;
    private Integer price;
    private Integer quantity;
    private Integer amount;
    private String skuIds;
    private Map<String,String> goodsSkuMap;
}

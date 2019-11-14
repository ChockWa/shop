package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.chock.shop.entity.GoodsDetail;
import org.chock.shop.entity.Sku;

import java.util.List;
import java.util.Map;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/14 15:07
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsItemDetail {
    private String goodsId;
    private String name;
    private String images;
    private String cover;
    private String description;
    private Integer price;
    private List<GoodsDetail> goodsDetailList;
    private Map<String, List<Sku>> goodsSkuMap;
}

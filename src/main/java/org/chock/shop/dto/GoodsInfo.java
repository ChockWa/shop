package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.chock.shop.entity.Sku;

import java.util.List;
import java.util.Map;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 18:21
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsInfo {
    private String goodsId;
    private String name;
    private String description;
    private String images;
    private Map<String, List<Sku>> skuMap;
}

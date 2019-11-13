package org.chock.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({ "createTime", "brandName", "updateTime" })
public class GoodsInfo {
    private String brandId;
    private String goodsId;
    private String name;
    private String description;
    private String cover;
    private String images;
    boolean status;
    private Map<String, List<Sku>> skuMap;
}

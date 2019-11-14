package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/14 13:44
 * @description:
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GoodsInfoItemQuery {
    private String name;
    private String brandId;
    private List<String> goodsIds;
}

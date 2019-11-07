package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.chock.shop.entity.GoodsDetail;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/7 11:02
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDetailDto extends GoodsDetail {
    private String skuNames;
    private String name;
}

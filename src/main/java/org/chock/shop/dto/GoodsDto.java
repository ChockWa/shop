package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.chock.shop.entity.Goods;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/7 10:43
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDto extends Goods {
    private String brandName;
}

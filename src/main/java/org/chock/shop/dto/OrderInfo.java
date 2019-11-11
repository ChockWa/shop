package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.chock.shop.entity.Order;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/11 17:15
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo extends Order {
    private String nickName;
    private String phone;
}

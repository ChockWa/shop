package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/12 09:27
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddOrderDto {
    private String orderToken;
    private Integer totalAmount;
    private String receiveAddressId;
}

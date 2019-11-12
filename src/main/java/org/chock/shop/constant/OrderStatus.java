package org.chock.shop.constant;

import lombok.Getter;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/12 17:17
 * @description:
 */
@Getter
public enum OrderStatus {
    UNPAY(1, "待付款"),
    UNSEND(2, "待发货"),
    UNRECEIVE(3, "待收货"),
    CHANGING(4, "换货中"),
    COMPLETED(5, "已完成");
    private int code;
    private String value;

    OrderStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }
}

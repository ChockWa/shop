package org.chock.shop.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 10:52
 * @description:
 */
@Getter
@Setter
public class BizException extends RuntimeException {
    public static BizException TOKEN_EXPIRED_ERROR = new BizException(1001, "会话超时，请重新登录");
    public static BizException AUTH_FAIL_ERROR = new BizException(1002, "授权失败，请重试");
    public static BizException ORDER_DATA_NULL_ERROR = new BizException(1003, "下单信息不能为空");
    public static BizException RECEIVE_ADDRESS_NULL_ERROR = new BizException(1004, "请选择收货地址");
    public static BizException ORDER_GOODS_NULL_ERROR = new BizException(1005, "请选择需要下单的商品");
    public static BizException AMOUNT_NOT_EQUAL_ERROR = new BizException(1006, "下单失败，金额不一致");
    public static BizException GOODS_EXIST_GUESS_LIKE_ERROR = new BizException(1007, "该商品已添加过");
    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;
}

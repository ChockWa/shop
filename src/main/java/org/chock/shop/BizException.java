package org.chock.shop;

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

    public static BizException AUTH_FAIL_ERROR = new BizException(1001, "授权失败，请重试");
    public static BizException TOKEN_EXPIRED_ERROR = new BizException(1002, "会话超时，请重新登录");

    public BizException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;
}

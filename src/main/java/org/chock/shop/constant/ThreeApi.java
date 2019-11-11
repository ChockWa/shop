package org.chock.shop.constant;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 10:12
 * @description:
 */
public class ThreeApi {
    // 登录凭证校验
    public static final String AUTH_CODE2SESSION = "https://api.weixin.qq.com/sns/jscode2session" +
            "?appid={appid}&secret={secret}&js_code={js_code}&grant_type={grant_type}";
}

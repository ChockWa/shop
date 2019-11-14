package org.chock.shop.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/14 14:36
 * @description:
 */
@Component
public class Constant {
    @Value("${dns.https}")
    public static String DNS_HTTPS;
}

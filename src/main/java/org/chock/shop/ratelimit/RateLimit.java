package org.chock.shop.ratelimit;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @auther: zhuohuahe
 * @date: 2019/12/25 11:01
 * @description:
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {

    String fallback() default "";

    int count() default 100;

    int time() default 1;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    String errorMsg() default "";
}

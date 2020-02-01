package org.chock.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.util.RedisUtils;
import org.chock.shop.util.VerifyCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auther: zhuohuahe
 * @date: 2019/3/28 18:02
 * @description:
 */
@RestController
@RequestMapping("auth")
@Slf4j
public class AuthorizationController {

    private static final long VERIFYCODE_EXPIRE_SECOND = 1000 * 60 * 5;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("genVerifyCode")
    public void generationVerifyCode(HttpServletResponse response, String uuid){
        try {
            String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
            VerifyCodeUtils.outputImage(80,38,response.getOutputStream(),verifyCode);
            redisUtils.set(uuid, verifyCode, VERIFYCODE_EXPIRE_SECOND);
        } catch (IOException e) {
            log.error("獲取驗證碼失敗", e);
        }
    }

}

package org.chock.shop.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.chock.shop.BizException;
import org.chock.shop.constant.ThreeApi;
import org.chock.shop.dto.Code2SessionResp;
import org.chock.shop.entity.User;
import org.chock.shop.util.RedisUtils;
import org.chock.shop.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 10:11
 * @description:
 */
@Service
@Slf4j
public class AuthService {

    // 10天
    private static final long TOKEN_EXPRIE_DAYS =  60 * 60 * 24 * 10;
    @Value("${wx-appid}")
    private String wxAppId;
    @Value("${wx-secret}")
    private String wxSecret;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisUtils redisUtils;

    public Code2SessionResp code2Session(String code){
        Map<String, Object> params = new HashMap<>(4);
        params.put("appid", wxAppId);
        params.put("secret", wxSecret);
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        try {
            Code2SessionResp resp = restTemplate.getForObject(ThreeApi.AUTH_CODE2SESSION, Code2SessionResp.class, params);
            if(resp != null){
                log.info("code2Session resp:{}", JSON.toJSON(resp));
                code2SessionExceptionHandle(resp);
                return resp;
            }
        }catch (Exception e){
            log.error("code2Session error!", e);
            throw BizException.AUTH_FAIL_ERROR;
        }
        return null;
    }

    private void code2SessionExceptionHandle(Code2SessionResp resp){
        if(-1 == resp.getErrCode() || 40029 == resp.getErrCode()){
            throw BizException.AUTH_FAIL_ERROR;
        }else if (0 == resp.getErrCode()){
            return;
        }else {
            throw new RuntimeException(String.format("未识别的code2Session错误:{%s:%s}", resp.getErrCode(), resp.getErrMsg()));
        }
    }

    public String generateToken(User user){
        String token = UUIDUtils.getUuid();
        redisUtils.set(token, user, TOKEN_EXPRIE_DAYS);
        return token;
    }

}

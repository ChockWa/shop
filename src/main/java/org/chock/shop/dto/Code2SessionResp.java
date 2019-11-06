package org.chock.shop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 10:18
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Code2SessionResp {
    @JsonProperty("openid")
    private String openId;
    @JsonProperty("session_key")
    private String sessionKey;
    @JsonProperty("unionid")
    private String unionId;
    @JsonProperty("errcode")
    private Integer errCode;
    @JsonProperty("errmsg")
    private String errMsg;
}

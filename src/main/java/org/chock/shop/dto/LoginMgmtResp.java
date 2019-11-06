package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 15:01
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginMgmtResp {
    private String token;
    private String userName;
}

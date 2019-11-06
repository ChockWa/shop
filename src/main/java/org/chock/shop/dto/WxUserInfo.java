package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 11:08
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WxUserInfo {

    private String nickName;
    private String avatarUrl;
    /**
     * 性别
     * 0：未知、1：男、2：女
     */
    private Integer gender;

}

package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/5 17:03
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_user")
public class User {
    @TableId(type = IdType.INPUT)
    private String uid;
    private String openId;
    private String name;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer status;
    private Integer gender;
    private String avatarUrl;
    private Date createTime;
}

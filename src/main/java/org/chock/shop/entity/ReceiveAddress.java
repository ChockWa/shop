package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/11 17:09
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_receive_address")
public class ReceiveAddress {
    @TableId(type = IdType.INPUT)
    private String id;
    private String uid;
    private String address;
    private String name;
    private String phone;
    private String province;
    private String city;
    private String district;
}

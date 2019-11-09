package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 15:49
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_sku")
public class Sku implements Serializable{
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.INPUT)
    private String id;
    private String code;
    private String name;
    private String value;
}

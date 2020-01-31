package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName("g_card")
public class GroupCard {
    @TableId(type = IdType.INPUT)
    private String cardNo;
    private String uid;
    private Integer status;
    private Date useTime;
    private Date createTime;
}

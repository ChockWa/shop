package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName("g_user")
public class GroupUser {
    @TableId(type = IdType.INPUT)
    private String uid;
    private String userName;
    private String password;
    private String salt;
    private String email;
    private Date vipEndTime;
    private Date createTime;
}

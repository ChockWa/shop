package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.omg.CORBA.IDLType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_category")
public class Category {
    @TableId(type = IdType.INPUT)
    private String id;
    private String name;
    private String logo;
    private Integer status;
}

package org.chock.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("s_brand")
public class Brand {
    @TableId(type = IdType.INPUT)
    private String id;
    private String nameZh;
    private String nameEn;
    private String logo;
    private Integer status;
}

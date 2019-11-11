package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.chock.shop.entity.Express;
import org.chock.shop.entity.ReceiveAddress;

import java.util.List;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/11 17:22
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailInfo {
    private String orderNo;
    private List<GoodsDetailInfo> goodsList;
    private Express express;
    private ReceiveAddress receiveAddress;
    private Integer totalAmount;
}

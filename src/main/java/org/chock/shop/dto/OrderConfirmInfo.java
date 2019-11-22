package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.chock.shop.entity.ReceiveAddress;

import java.util.List;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/22 13:49
 * @description:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmInfo {
    private Integer totalAmount;
    private ReceiveAddress receiveAddress;
    private List<GoodsDetailInfo> goodsDetailInfos;
}

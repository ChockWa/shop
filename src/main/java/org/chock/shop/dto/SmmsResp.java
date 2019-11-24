package org.chock.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmmsResp {

    //{"success":true,"code":"success","message":"Upload success.","data":{"file_id":0,"width":565,"height":336,"filename":"d4cdd5ae-3e0f-4f2b-893e-e92dfb9edd76.jpg","storename":"lafxhn37DJKHdIC.png","size":12340,"path":"\/2019\/11\/24\/lafxhn37DJKHdIC.png","hash":"GEiLYZ7Mcaz3qxRn9hS1ptmfQ5","url":"https:\/\/i.loli.net\/2019\/11\/24\/lafxhn37DJKHdIC.png","delete":"https:\/\/sm.ms\/delete\/GEiLYZ7Mcaz3qxRn9hS1ptmfQ5","page":"https:\/\/sm.ms\/image\/lafxhn37DJKHdIC"},"RequestId":"C67FEE36-E15B-43F3-9F16-094E7E038099"}
    private Boolean success;
    private String code;
    private String message;
    private SmmsRespData data;
}

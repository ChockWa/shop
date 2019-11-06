package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.dto.WxUserInfo;
import org.chock.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 14:50
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/wxLogin")
    public Result wxLogin(String code, WxUserInfo wxUserInfo){
        return Result.SUCCESS().setData("token", userService.wxLogin(wxUserInfo, code));
    }
}

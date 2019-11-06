package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 15:18
 * @description:
 */
@RestController
@RequestMapping("/um")
public class UserMgmtController {

    @Autowired
    private UserService userService;

    @GetMapping("/lm")
    public Result loginMgmt(String email, String password){
        return Result.SUCCESS().setData(userService.loginMgmt(email, password));
    }
}

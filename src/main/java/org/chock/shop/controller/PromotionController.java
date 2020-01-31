package org.chock.shop.controller;

import org.chock.shop.dto.Result;
import org.chock.shop.entity.GroupUser;
import org.chock.shop.exception.BizException;
import org.chock.shop.service.GroupUserService;
import org.chock.shop.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PromotionController {

    @Autowired
    private GroupUserService groupUserService;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index", "name", "hehehehehehe");
    }

    @GetMapping("/regP")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @PostMapping("reg")
    @ResponseBody
    public Result register(@RequestBody GroupUser user){
        return Result.SUCCESS().setData(groupUserService.register(user.getUserName(), user.getPassword(), user.getEmail()));
    }

    @PostMapping("login")
    @ResponseBody
    public Result login(@RequestBody GroupUser user){
        return Result.SUCCESS().setData(groupUserService.login(user.getUserName(), user.getPassword()));
    }

    @GetMapping("charge")
    @ResponseBody
    public Result chargeVip(String cardNo, HttpServletRequest request){
        String token = request.getParameter("groupT");
        checkLogin(token);
        groupUserService.chargeVip(cardNo, token);
        return Result.SUCCESS();
    }

    @GetMapping("expire")
    @ResponseBody
    public Result checkExpire(String userName){
        groupUserService.checkExpire(userName);
        return Result.SUCCESS();
    }

    public void checkLogin(String groupT){
        if(!JwtUtils.verifyToken(groupT)){
            throw BizException.TOKEN_EXPIRED_ERROR;
        }
    }
}

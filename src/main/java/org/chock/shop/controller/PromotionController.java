package org.chock.shop.controller;

import org.apache.commons.lang3.StringUtils;
import org.chock.shop.dto.LoginRequest;
import org.chock.shop.dto.Result;
import org.chock.shop.exception.BizException;
import org.chock.shop.service.GroupUserService;
import org.chock.shop.util.JwtUtils;
import org.chock.shop.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PromotionController {

    @Autowired
    private GroupUserService groupUserService;
    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("index", "name", "hehehehehehe");
    }

    @GetMapping("/regP")
    public ModelAndView registerPage() {
        return new ModelAndView("register");
    }

    @GetMapping("/loginP")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    @GetMapping("/chargeP")
    public ModelAndView chargePage() {
        return new ModelAndView("charge");
    }

    @PostMapping("reg")
    @ResponseBody
    public Result register(@RequestBody LoginRequest request){
        checkVerifyCode(request.getUuid(), request.getVerifyCode());
        return Result.SUCCESS().setData(groupUserService.register(request.getUserName(), request.getPassword(), request.getEmail()));
    }

    @PostMapping("login")
    @ResponseBody
    public Result login(@RequestBody LoginRequest request){
        checkVerifyCode(request.getUuid(), request.getVerifyCode());
        return Result.SUCCESS().setData(groupUserService.login(request.getUserName(), request.getPassword()));
    }

    @GetMapping("/charge/{cardNo}")
    @ResponseBody
    public Result chargeVip(@PathVariable String cardNo, HttpServletRequest request){
        String token = request.getHeader("groupT");
        checkLogin(token);
        return Result.SUCCESS().setData(groupUserService.chargeVip(cardNo, token));
    }

    @GetMapping("expire")
    @ResponseBody
    public Result checkExpire(String userName){
        groupUserService.checkExpire(userName);
        return Result.SUCCESS();
    }

    private void checkLogin(String groupT){
        if(!JwtUtils.verifyToken(groupT)){
            throw BizException.TOKEN_EXPIRED_ERROR;
        }
    }

    private void checkVerifyCode(String uuid, String verifyCode){
        if(StringUtils.isBlank(uuid) || StringUtils.isBlank(verifyCode)){
            throw new IllegalArgumentException("验证码不能为空");
        }
        if(!verifyCode.equalsIgnoreCase((String) redisUtils.get(uuid))){
            throw new IllegalStateException("验证码错误");
        }
    }
}

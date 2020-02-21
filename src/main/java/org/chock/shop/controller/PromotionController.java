package org.chock.shop.controller;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.dto.LoginRequest;
import org.chock.shop.dto.Result;
import org.chock.shop.exception.BizException;
import org.chock.shop.ratelimit.RateLimit;
import org.chock.shop.service.GroupUserService;
import org.chock.shop.util.JwtUtils;
import org.chock.shop.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
@Controller
public class PromotionController extends BaseController {

    private static final String CURRENT_VERSION = "1.0";

    @Autowired
    private GroupUserService groupUserService;
    @Autowired
    private RedisUtils redisUtils;
    @Value("${request.prefix}")
    private String requestPrefix;

    @RateLimit
    @GetMapping("/index")
    public ModelAndView index() {
        System.out.println(requestPrefix);
        return new ModelAndView("index", "requestPrefix", requestPrefix);
    }

    @RateLimit
    @GetMapping("/regP")
    public ModelAndView registerPage() {
        return new ModelAndView("register", "requestPrefix", requestPrefix);
    }

    @RateLimit
    @GetMapping("/loginP")
    public ModelAndView loginPage() {
        return new ModelAndView("login", "requestPrefix", requestPrefix);
    }

    @RateLimit
    @GetMapping("/chargeP")
    public ModelAndView chargePage() {
        return new ModelAndView("charge", "requestPrefix", requestPrefix);
    }

    @RateLimit
    @PostMapping("reg")
    @ResponseBody
    public Result register(@RequestBody LoginRequest request){
        checkVerifyCode(request.getUuid(), request.getVerifyCode());
        return Result.SUCCESS().setData(groupUserService.register(request.getUserName(), request.getPassword(), request.getEmail()));
    }

    @RateLimit
    @PostMapping("login")
    @ResponseBody
    public Result login(@RequestBody LoginRequest request){
        checkVerifyCode(request.getUuid(), request.getVerifyCode());
        return Result.SUCCESS().setData(groupUserService.login(request.getUserName(), request.getPassword()));
    }

    @RateLimit
    @GetMapping("/charge/{cardNo}")
    @ResponseBody
    public Result chargeVip(@PathVariable String cardNo, HttpServletRequest request){
        String token = request.getHeader("groupT");
        checkLogin(token);
        return Result.SUCCESS().setData(groupUserService.chargeVip(cardNo, token));
    }

    @RateLimit
    @GetMapping("expire")
    @ResponseBody
    public Result checkExpire(String userName, String version){
        if(StringUtils.isBlank(version) || !CURRENT_VERSION.equals(version)){
            throw new BizException(9999, "请到官网下载最新版本使用");
        }
        groupUserService.checkExpire(userName);
        return Result.SUCCESS();
    }

    @RateLimit
    @GetMapping("checkL")
    @ResponseBody
    public Result checkLogin(HttpServletRequest request){
        String token = request.getHeader("groupT");
        checkLogin(token);
        return Result.SUCCESS();
    }

    private void checkLogin(String groupT){
        if(!JwtUtils.verifyToken(groupT)){
            throw BizException.TOKEN_EXPIRED_ERROR;
        }
    }

    private void checkVerifyCode(String uuid, String verifyCode){
        if(StringUtils.isBlank(uuid) || StringUtils.isBlank(verifyCode)){
            throw new BizException(9999, "验证码不能为空");
        }
        if(!verifyCode.equalsIgnoreCase((String) redisUtils.get(uuid))){
            throw new BizException(9999, "验证码错误");
        }
    }

    @RateLimit
    @GetMapping("downloadUB")
    public void downloadUseBook(HttpServletResponse response){
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("简易群发工具图文教程", "UTF-8") + ".docx");
            downloadUseInstrucntion(response);
        } catch (UnsupportedEncodingException e) {
            log.error("编码失败", e);
        }
    }

    @RateLimit
    @GetMapping("downloadTool")
    public void downloadTool(HttpServletResponse response){
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode("简易群发工具图文教程", "UTF-8") + ".docx");
            downloadT(response);
        } catch (UnsupportedEncodingException e) {
            log.error("编码失败", e);
        }
    }

    /**
     * 下载使用说明
     * @param response
     */
    public void downloadUseInstrucntion(HttpServletResponse response){
        String path = "/files/GroupSend.docx";
        File file = new File(path);
        try {
            @Cleanup FileInputStream is = new FileInputStream(file);
            byte[] bs = new byte[1024];
            int len = 0;
            while ((len=is.read(bs)) != -1){
                response.getOutputStream().write(bs, 0, len);
            }
        } catch (IOException e) {
            log.error("下载失败", e);
        }
    }

    /**
     * 下载工具
     * @param response
     */
    public void downloadT(HttpServletResponse response){
        String path = "/files/GroupSend.zip";
        File file = new File(path);
        try {
            @Cleanup FileInputStream is = new FileInputStream(file);
            byte[] bs = new byte[8192];
            int len = 0;
            while ((len=is.read(bs)) != -1){
                response.getOutputStream().write(bs, 0, len);
            }
        } catch (IOException e) {
            log.error("下载失败", e);
        }
    }
}

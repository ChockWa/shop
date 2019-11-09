package org.chock.shop.config;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;
import org.chock.shop.exception.BizException;
import org.chock.shop.dto.UserInfo;
import org.chock.shop.entity.User;
import org.chock.shop.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @auther: zhuohuahe
 * @date: 2019/3/28 14:17
 * @description:
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final ImmutableSet<String> NO_NEED_LOGIN_URIS = ImmutableSet.of("/user/mgmtLogin");
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 檢查ip
        if(checkNeedLoginOrNot(request.getRequestURI())){
            String token = request.getHeader("beautyT");
            User user = (User) redisUtils.get(token);
            if(StringUtils.isBlank(token) || user == null){
                throw BizException.TOKEN_EXPIRED_ERROR;
            }
            UserInfo.set(user);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        UserInfo.clear();
        addLog(request);
    }

    private void addLog(HttpServletRequest request){
//        LogEventProducer producer = new LogEventProducer(new LogEventTranslator(), logEventDisruptor.getRingBuffer());
//        Log log = new Log();
//        log.setMethod(request.getRequestURI());
//        log.setIp(getIpAddress(request));
//        log.setParams(Objects.nonNull(UserInfo.get()) ? String.format("{uid:%s}", UserInfo.get().getUid()) : "");
//        log.setCreateTime(new Date());
//        producer.recordLog(log);
    }

    private boolean checkNeedLoginOrNot(String uri){
        if(StringUtils.isBlank(uri)){
            throw new RuntimeException("uri is blank");
        }
        return !NO_NEED_LOGIN_URIS.contains(uri);
    }

    private String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}

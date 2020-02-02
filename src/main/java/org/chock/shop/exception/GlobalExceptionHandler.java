package org.chock.shop.exception;

import lombok.extern.slf4j.Slf4j;
import org.chock.shop.dto.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther: zhuohuahe
 * @date: 2019/3/28 18:31
 * @description:
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 记录该次请求的异常
     */
    public static final ThreadLocal<Result> exceptionMsg = new ThreadLocal<>();

    @ExceptionHandler(value=BizException.class)
    public Result bizExceptionHandle(HttpServletRequest request, Exception exception) throws Exception{
        BizException bizException = (BizException) exception;
        Result result = Result.FAIL(bizException.getCode(), bizException.getMessage());
        exceptionMsg.set(result);
        return result;
    }

    @ExceptionHandler(value=Exception.class)
    public Result exceptionHandle(HttpServletRequest request, Exception exception) throws Exception{
        log.error("Exception：", exception);
        Result result = Result.FAIL(9999, "系统不舒服,请稍后重试");
        exceptionMsg.set(result);
        return result;
    }
}

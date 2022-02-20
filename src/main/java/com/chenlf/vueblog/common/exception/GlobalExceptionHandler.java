package com.chenlf.vueblog.common.exception;

import cn.hutool.http.HttpRequest;
import com.chenlf.vueblog.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;


/**
 * 全局异常处理
 * @author ChenLF
 * @date 2022/02/20 10:35
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handle401(ShiroException e){
        log.error("运行时异常:------------------------");
        return Result.fail(401,e.getMessage(),null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e)throws IOException {
        log.error("运行时异常:------------------------");
        return Result.fail(e.getMessage());
    }

}

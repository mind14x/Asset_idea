package com.asset.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理所有不明确的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleException(Exception e, HttpServletRequest request) {
        // 这里可以打印异常信息到日志
        e.printStackTrace();
        return "URL " + request.getRequestURL() + " 发生了错误：" + e.getMessage();
    }

    /**
     * 处理特定的异常，比如404错误
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        return "URL " + request.getRequestURL() + " 未找到";
    }

    // 你可以继续为其他特定的异常定义处理器...

}
package com.qywk.common.exception;

import com.qywk.common.entity.ResultBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) // 捕获所有异常
    public ResultBody exception(Exception ex){
        ex.printStackTrace();
        return ResultBody.error().message("对不起，操作失误，请联系管理员");
    }
}

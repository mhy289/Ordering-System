package com.zqu.ordersystem.advice;

import com.zqu.ordersystem.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  //用于标识当前类为REST风格对应的异常处理器
public class ProjectExceptionAdvice {

    //统一处理所有的Exception异常
    @ExceptionHandler(Exception.class)
    public Result doOtherException(Exception ex){
        Result result = new Result(null,"系统错误",500);
        result.setData(ex.getMessage());
        return result;
    }
}
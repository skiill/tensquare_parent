package com.tensquare.base.controller;

import ch.qos.logback.core.status.Status;
import entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xsj
 */
@RestControllerAdvice
public class BaseExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    public Result exception(Exception e){
        return new Result(false, Status.ERROR,e.getMessage());
    }

}

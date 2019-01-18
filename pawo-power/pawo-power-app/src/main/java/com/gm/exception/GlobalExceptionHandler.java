/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.gm.exception;

import com.gm.error.ResponseBodyBasic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

import jdk.nashorn.internal.objects.Global;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 全局异常处理 </p>
 *
 * <pre> Created: 2019-01-17 14:25  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler implements Serializable{

    private static final long serialVersionUID = 1128323742689049714L;

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 自定义异常处理
     *
     * @param e 自定义抛出异常
     * @return 页面返回结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = PawoException.class)
    @ResponseBody
    public ResponseBodyBasic basic(PawoException e){
        LOGGER.error("pawo has exception! {}",e);
        return ResponseBodyBasic.builder()
                .code(e.getErrorCode())
                .message(e.getMessage()).build();
    }


    /**
     * 500 默认处理
     *
     * @param e 系统抛出异常
     * @return 页面结果
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseBodyBasic basic(Exception e){
        LOGGER.error("pawo has exception! {}",e);
        return ResponseBodyBasic.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage()).build();
    }

}

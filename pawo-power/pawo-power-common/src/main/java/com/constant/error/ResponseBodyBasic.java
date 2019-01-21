/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.constant.error;

import java.io.Serializable;

import lombok.Data;

/**
 * <p> 返回异常信息类 </p>
 *
 * <pre> Created: 2019-01-17 13:48  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class ResponseBodyBasic implements Serializable{

    private static final long serialVersionUID = -2977719628763906255L;
    /**
     * 错误码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;


    private static int DEFAULT_CODE = 0;

    private static String DEAFULT_MESSAGE = "执行失败！";

    private ResponseBodyBasic(int code,String message){
        this.code = code;
        this.message = message;
    }

    public static ResponseBodyBasic ok(){
        return new ResponseBodyBasicBuilder().code(DEFAULT_CODE).message(DEAFULT_MESSAGE).build();
    }


    public static ResponseBodyBasicBuilder builder(){
        return new ResponseBodyBasicBuilder();
    }

    public static class ResponseBodyBasicBuilder{

        /**
         * 错误代码
         */
        private int code;
        /**
         * 错误信息
         */
        private String message;

        private ResponseBodyBasicBuilder(){}

        public ResponseBodyBasicBuilder code(int code){
            this.code = code;
            return this;
        }

        public ResponseBodyBasicBuilder message(String message){
            this.message = message;
            return this;
        }

        public ResponseBodyBasic build(){
            return new ResponseBodyBasic(this.code,this.message);
        }
    }


}

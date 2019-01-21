/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao Inc.
 * pawo-common All rights reserved.
 */

package com.gm.config.exception;
/**
 * <p> 异常信息扩展 </p>
 *
 * <pre> Created: 2019-01-17 14:32  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class PawoException extends RuntimeException {

    private static final long serialVersionUID = -6123745728054906909L;

    /**
     * 错误码
     */
    private final int errorCode;

    /**
     * 扩展内容
     */
    private final Object ext;


    public PawoException(int errorCode){
        super();
        this.errorCode = errorCode;
        this.ext = null;
    }

    public PawoException(String message,int errorCode){
        super(message);
        this.errorCode = errorCode;
        this.ext = null;
    }

    public PawoException(String message,int errorCode,Throwable throwable){
        super(message,throwable);
        this.ext = null;
        this.errorCode = errorCode;
    }


    public PawoException(String message,int errorCode,Object obj){
        super(message);
        this.ext = obj;
        this.errorCode = errorCode;
    }


    /**
     * 获取错误码
     *
     * @return 错误码
     */
    public int getErrorCode(){
        return this.errorCode;
    }


    /**
     * 获取错误扩展信息
     *
     * @return 扩展信息
     */
    public Object getExt(){
        return this.ext;
    }
}

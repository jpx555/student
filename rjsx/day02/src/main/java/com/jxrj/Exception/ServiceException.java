package com.jxrj.Exception;

import lombok.Getter;

/**
 * +----------------------------------------------------------------------
 * | @Description : 自定义一个业务类的异常
 * +----------------------------------------------------------------------
 * | @Author : CloudNET_施云凯
 * +----------------------------------------------------------------------
 * | @CreateTime : 2022/11/27 10:46
 * +----------------------------------------------------------------------
 */
@Getter
public class ServiceException extends RuntimeException {
    private String code;

    public ServiceException(String code,String msg) {
        super(msg);
        this.code = code;
    }
}


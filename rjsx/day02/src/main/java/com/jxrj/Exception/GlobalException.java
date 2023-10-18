package com.jxrj.Exception;

import com.jxrj.common.ResultMessage;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResultMessage serviceException(ServiceException e){
        return ResultMessage.error("500",e.getMessage());
    }
}

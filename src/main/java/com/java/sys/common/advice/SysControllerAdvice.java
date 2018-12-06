package com.java.sys.common.advice;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.common.constance.ApiConstance;
import com.java.sys.common.basic.controller.BaseController;
import com.java.sys.common.basic.result.BaseResult;


@ControllerAdvice(basePackages = {"com.java.controller.api","com.java.controller.ajax"})
public class SysControllerAdvice extends BaseController{
	
	
	
	//400错误->缺少参数异常
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResponseEntity<BaseResult> requestMissingServletRequest(MissingServletRequestParameterException ex){
    	return buildFailedInfo(ApiConstance.PARAM_IS_NULL);
    }
    
    
    
    //400错误->参数类型异常
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public ResponseEntity<BaseResult> requestTypeMismatch(TypeMismatchException ex){
    	return buildFailedInfo(ApiConstance.PARAM_TYPE_ERROR);
    }
    
    
}

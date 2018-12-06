package com.java.sys.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.java.sys.common.utils.Tool;

@Component
public class SysExceptionHandler extends SimpleMappingExceptionResolver {
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request,HttpServletResponse response, Object obj, Exception e) {
		response.setCharacterEncoding("UTF-8");
		if(e instanceof org.apache.shiro.authz.UnauthorizedException){
			return new ModelAndView("/WEB-INF/views/shiro/unauth.jsp");
		}else if(e instanceof org.springframework.web.multipart.MultipartException){
			return new ModelAndView("/WEB-INF/views/sys/noFileReq.jsp");
		}else if(e instanceof java.io.IOException){
			Tool.warn("--- : java.io.IOException",this.getClass());
			Tool.warn(e.getMessage(),this.getClass());
		}else if(e instanceof IllegalArgumentException){
			Tool.warn("--- : java.lang.IllegalArgumentException",this.getClass());
			Tool.warn(e.getMessage(),this.getClass());
		}else{
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw,true));
			Tool.error(sw.toString(), this.getClass());
		}
		return null;
	}
	
}

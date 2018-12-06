package com.java.sys.config.listener;

import java.util.EventListener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.java.sys.common.listener.SysServletContextListener;

@Configuration
public class ListenerConfig {
	
	
	
	@Bean
	public ServletListenerRegistrationBean<EventListener> sysServletContextListener(){
		ServletListenerRegistrationBean<EventListener> bean = new ServletListenerRegistrationBean<EventListener>();
		bean.setListener(new SysServletContextListener());
		return bean;
	}
	
	
}

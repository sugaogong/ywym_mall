package com.java.sys.config.interceptor;

import com.java.common.interceptor.DDOSInterceptor;
import com.java.common.interceptor.annotation.DDOSValidate;
import com.java.sys.common.interceptor.CorsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.java.common.interceptor.LoginInterceptor;
import com.java.sys.common.interceptor.XSSInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

	
	@Bean
	XSSInterceptor xssInterceptor() {
		return new XSSInterceptor();
	}
	
	@Bean
	LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
	@Bean
	DDOSInterceptor ddosInterceptor() {
		return new DDOSInterceptor();
	}

	@Bean
	CorsInterceptor corsInterceptor() { return new CorsInterceptor();}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(corsInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(xssInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(ddosInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}



}

package com.java.sys.config.filter;

import com.java.sys.common.filter.ShutdownFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean shutdownFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new ShutdownFilter());
    	bean.addUrlPatterns("/shutdown");
        bean.setName("shutdownFilter");
        bean.setOrder(2);
        return bean;
	}
	
	
	
	
	
	
	
}

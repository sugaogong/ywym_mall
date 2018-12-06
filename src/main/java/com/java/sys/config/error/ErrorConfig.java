package com.java.sys.config.error;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/*@Configuration
public class ErrorConfig {
	
	
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	    return new EmbeddedServletContainerCustomizer() {
	        @Override
	        public void customize(ConfigurableEmbeddedServletContainer container) {
	            //ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html");
	            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	            container.addErrorPages(error404Page);
	        }
	    };
	}
	
	
	
	
	
}*/

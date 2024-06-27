package com.poscodx.mysite.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.poscodx.mysite.event.ApplicationContextEventListener;

@Configuration
@EnableAspectJAutoProxy
@Import({MvcConfig.class, LocaleConfig.class, SecurityConfig.class})
@ComponentScan({"com.poscodx.mysite.controller", "com.poscodx.mysite.exception"})
public class WebConfig {
	
	//Site Interceptor
	@Bean
	public HandlerInterceptor siteInterceptor(InterceptorRegistry registry) {
		registry.addInterceptor(siteInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/assets/**");
		
	}
	
	// ApplicationContext Event Listener
	@Bean
	public ApplicationContextEventListener applicationContextEventListener() {
		return new ApplicationContextEventListener();
	}
}

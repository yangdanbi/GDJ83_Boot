package com.db.app.configs;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration //xml처럼 쓰겠다
public class MessageConfig implements WebMvcConfigurer{
	
	@Bean
	LocaleResolver localeResolver() {
		//1. session을 이용하는 객체
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		//2. Cookie
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setDefaultLocale(Locale.KOREAN);
		
	 return cookieLocaleResolver;
	
	}
	@Bean
	LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		
		//parameter 을 받아서 언어를 구분
		//url?lang=ko
		//이런식으로 파라미터를 보내면 이 정보를 쿠키에 담던 세션에 담던 담아서 처리
		return changeInterceptor;
		
	}
	

}

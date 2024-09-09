package com.db.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.db.app.interceptors.AdminCheckInterceptor;
import com.db.app.interceptors.LoginInterceptor;

@Configuration
public class interceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	@Autowired
	private AdminCheckInterceptor adminCheckInterceptor;
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//어떤 URL이 왔을 때 어떤 Intercetor를 실행 할 것인가??
		// /qna/list -> LoginInterceptor 를 거치게 하자
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/qna/*")  //qna의 모든 것에 다 제한걸어놓고 
				.excludePathPatterns("/qna/list"); //qna 리스트는 로그인을 안해도 볼수 있게 해제
		
		registry.addInterceptor(adminCheckInterceptor)
				.addPathPatterns("/admin/*");  //qna의 모든 것에 다 제한걸어놓고 
		registry.addInterceptor(localeChangeInterceptor)
		.addPathPatterns("/**");  //qna의 모든 것에 다 제한걸어놓고 
	}

}

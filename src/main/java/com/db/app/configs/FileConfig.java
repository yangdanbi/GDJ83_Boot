package com.db.app.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

//설정 class
// @Configuration 을 주면 스프링이 설정파일이라고 생각함 xml느낌
@Configuration
//WebMVCConfigure 구현  => WebMVCConfigure 는 인터페이스 => 오버라이드해야함
@Slf4j
public class FileConfig implements WebMvcConfigurer{
	@Value("${app.url.path}")
	private String url;
	
	@Value("${app.upload.location}")
	private String file;
//위에 두개를 아래 addResourceHandlers 여기서 핸들링함
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//<resources mapping="/resources/**" location="/resources/" />
		//<resources mapping="/files/**" location="D:/upload/" />
		
		log.info("==============");
		log.info("url");
		log.info("file");
		registry.addResourceHandler(url)
				.addResourceLocations(file);
	}
}

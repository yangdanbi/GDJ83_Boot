package com.db.app.configs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		//security에서 무시할 것들
		return web -> web
						.ignoring() //무시한다
						.requestMatchers("/images/**")
						.requestMatchers("/css/**")
						.requestMatchers("/js/**")
						.requestMatchers("/venders/**")
						.requestMatchers("/favicon/**")
						;
	}
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
		security
				.cors()
				.and()
				.csrf()
				.disable();
			//권한에 관련된 설정
		security
			.authorizeHttpRequests(
					(authorizeRequest) ->
						authorizeRequest 
							.requestMatchers("/").permitAll()
							.requestMatchers("/qna/list").permitAll()
							.requestMatchers("/qna/detail","/qna/add","/qna/update","/qna/delete").authenticated()
							.requestMatchers("/notice/list", "/notice/detail").permitAll()
							.requestMatchers("/notice/*").hasRole("ADMIN")
							.requestMatchers("/manager/*").hasAnyRole("MANAGER","ADMIN")
							.requestMatchers("/member/add","/member/login").permitAll()
							.requestMatchers("/member/*").authenticated()
							.anyRequest().permitAll()
							
				)//authorizeHttpRequests 끝
			
			//form login에 관련 설정
			.formLogin(
					login -> 
						login.loginPage("/member/login")
						.defaultSuccessUrl("/")//로그인이 성공했을때 어디로 보낼거냐
						.failureUrl("/member/login")
						//파라미터 이름이 username이 아니고 id로 사용한 경우
						//.usernameParameter(null)//로그인 진행을 security가 하는데 사용자가 입력한 정보(파라미터)를 마음대로 꺼낼수 없기 때문에
						//파라미터 이름이 password가 아니고 'pw'로 사용한 경우
						//.passwordParameter("pw")
						.permitAll()
					)
			
			;
		
		return security.build();// 위 설정 정보를 가지고 객체를 만들어서 리턴
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

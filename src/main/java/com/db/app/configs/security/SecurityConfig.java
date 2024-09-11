package com.db.app.configs.security;

import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.db.app.members.MemberUserService;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private SecurityLoginSuccessHandler handler;
	
	@Autowired
	private SecurityLoginFailHandler failHandler;
	
	@Autowired
	private MemberUserService memberUserService;
	

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
		String message = URLEncoder.encode("로그인실패","UTF-8");
		
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
							.requestMatchers("/notice/list", "/notice/detail").permitAll()
							.requestMatchers("/notice/*").hasRole("ADMIN")
							.requestMatchers("/qna/list").permitAll()
							.requestMatchers("/qna/*").authenticated()
							.requestMatchers("/manager/*").hasAnyRole("MANAGER","ADMIN")
							.requestMatchers("/member/add","/member/login","/member/check").permitAll()
							.requestMatchers("/member/*").authenticated()
							.anyRequest().permitAll()
							
				)//authorizeHttpRequests 끝
				
			//form login에 관련 설정
			.formLogin(
					login -> 
						//개발자가 만든 로그인 페이지 사용
						login.loginPage("/member/login")
						//.defaultSuccessUrl("/")//로그인이 성공했을때 어디로 보낼거냐
						.successHandler(handler)
						//.failureUrl("/member/login?message="+message)
						.failureHandler(failHandler)
						//파라미터 이름이 username이 아니고 id로 사용한 경우
						//.usernameParameter(null)//로그인 진행을 security가 하는데 사용자가 입력한 정보(파라미터)를 마음대로 꺼낼수 없기 때문에
						//파라미터 이름이 password가 아니고 'pw'로 사용한 경우
						//.passwordParameter("pw")
						.permitAll()
					)
					//logout 설정 
			.logout(
					logout -> 
					logout
						//RequestMatcher("url") , 로그아웃 url 지정
						.logoutUrl("/member/logout")//로그아웃 url 지정
						//.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) 둘중 골라서 사용
						.logoutSuccessUrl("/")
						.invalidateHttpSession(true) //ture 면 session 만료, false면 만료x
						//.deleteCookies("")//cookie 삭제하는 법
					)
					//rememberMe 자동로그인 
				.rememberMe(
						remember -> 
							remember
								.rememberMeParameter("rememberMe")
								//token유효시간
								.tokenValiditySeconds(60)
								//token에 생성시 값, 필수 값, 개발자가 값 설정
								.key("rememberme")
								//인증절차(로그인)진행 할 UserDetailService
								.userDetailsService(memberUserService)
								//로그인이 성공했을 경우 진행할 handler
								.authenticationSuccessHandler(handler)
								.useSecureCookie(false)
						)
				//동시세션
				.sessionManagement(
						sessionManager -> 
							sessionManager
								.maximumSessions(1)//최대허용 갯수, -1이면 무한대 허용 0은 동시접속x
								//false 기존사용자 만료, true 새로운 사용자 인증 실패
								.maxSessionsPreventsLogin(false)
								//세션이 만료되었을 경우 리다이렉트 할 URL
								.expiredUrl("/member/check")
								
								
								
								
						)
				
			;
		
		return security.build();// 위 설정 정보를 가지고 객체를 만들어서 리턴
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

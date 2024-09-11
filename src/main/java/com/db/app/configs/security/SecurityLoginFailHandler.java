package com.db.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import javax.security.auth.login.CredentialNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.db.app.members.MemberMapper;
import com.db.app.members.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SecurityLoginFailHandler implements AuthenticationFailureHandler {
	@Autowired
	private MemberMapper memberMapper;
	
	@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
		String message = "로그인 실패";
				//URLEncoder.encode("로그인 실패","UTF-8");
		log.info("Exception : {} ", exception);
		
		if(exception instanceof BadCredentialsException) {
			//비번 틀림
			message="비밀번호를 확인해주세요"; 
		}
		if (exception instanceof InternalAuthenticationServiceException){
			//아이디 틀림
			message="없는 ID입니다.";
		}
		if (exception instanceof AccountExpiredException){
			//계정 유효기간 만료
			message="만료된 ID입니다. 관리자에게 문의하세요.";
		}
		if (exception instanceof LockedException){
			//계정이 잠김
			message="계정이 잠겼습니다. 관리자에게 문의하세요.";
		}
		if (exception instanceof CredentialsExpiredException){
			//비번 유효기간 만료
			message="만료된 비밀번호입니다. 관리자에게 문의하세요.";
		}
		if (exception instanceof DisabledException){
			//휴먼계정
			message="휴먼계정입니다. 관리자에게 문의하세요.";
		}
		
		message = URLEncoder.encode(message,"UTF-8");
		
		response.sendRedirect("/member/login?message="+message);
		}
}

package com.db.app.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.db.app.members.MemberVO;
import com.db.app.members.RoleVO;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
// 권한이 관리자 권한이 있는애만 통과시킴
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		MemberVO memberVO = (MemberVO) request.getSession().getAttribute("member");
		
		if(memberVO == null) {
			response.sendRedirect("/member/login");
			return false;
		}
		// 여기서 role 정보를 꺼내야함
		for (RoleVO role : memberVO.getVos()) {
			if (role.getRoleName().equals("ROLE_ADMIN")) {
				return true;
			}
		}
		
		request.setAttribute("msg","관리자 전용");
		request.setAttribute("path","/");
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
		view.forward(request, response);
		return false;
	}

}

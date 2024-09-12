<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- c처럼 스프링에서 제공하는 라이브러리 -->
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Index Page</h1>
	<img alt="" src="/images/dog1.jpg">
	<spring:message code="hello"></spring:message>
	<spring:message code="hello2" text="기본값"></spring:message>
	
	<!--  isAuthenticated : 인증이 성공했을때  -->
	<sec:authorize access="!isAuthenticated()">
		<h1>Login 전</h1>
		<a href="/member/login">Login</a>
		<a href="/oauth2/authorization/kakao?prompt=login">KaKao Login</a>
	</sec:authorize>
	
    <sec:authorize access="isAuthenticated()">
		<h1>Login 성공</h1>
		<sec:authentication property="principal" var="member"/>
		<spring:message code="member.login.message" arguments="${member.username},${member.email}" argumentSeparator=","> </spring:message>
		
		
		<c:forEach items="${member.vos}" var="r">
			<h2>${r.roleName}</h2>
		</c:forEach>
	</sec:authorize>
	
	
	<sec:authorize access="hasRole('ADMIN')">
		<h1>관리자 전용</h1>
	</sec:authorize>
	
</body>
</html>
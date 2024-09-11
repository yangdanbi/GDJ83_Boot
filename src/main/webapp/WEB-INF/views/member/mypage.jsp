<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>My Page</h1>
	<!-- 출력하는 방법은 여러가지 -->
	<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="vo"/> 
		<h3>username : ${vo.username}</h3><!-- 변수에 담아도 됨 -->
		<h3>name : ${vo.name}</h3>
		<h3>email : <sec:authentication property="principal.email"/></h3><!-- principal에서 바로 꺼내도 됨 -->
		<h3>name : <sec:authentication property="name"/></h3>
		<%-- <h3>${member.birth}</h3> --%>
	
	<a href="./update">회원수정</a>
	
	</sec:authorize>
	
</body>
</html>
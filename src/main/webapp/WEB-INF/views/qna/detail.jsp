<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Detail Page</h1>
	<h3>${vo.boardTitle}</h3>
	<h3>${vo.boardWriter}</h3>
	<h3>${vo.boardContents}</h3>
	
	<c:forEach items="${vo.ar}" var="f">
		<img src="/files/${board}/${f.fileName}">	
	<!--D:/upload 까지 매핑  -->
	</c:forEach>
</body>
</html>
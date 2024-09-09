<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
			<!-- action을 따로 안하면 현재 위치의 url이 옴 -->
				<form:form method="post" modelAttribute="memberVO">
				  <div class="mb-3">
				    <label for="username" class="form-label">ID</label>
				    <form:input cssClass="form-control" id="username" path="username"/>
				    <div>
				    	<form:errors path="username"></form:errors>
				    </div>
				  </div>
	 	  				  
				   <div class="mb-3">
				    <label for="name" class="form-label">NAME</label>
				    <form:input  cssClass="form-control" id="name" path="name"/>
				     <div>
				    	<form:errors path="name"></form:errors>
				    </div>
				  </div>
				  <div class="mb-3">
				  	<label for="email" class="form-label">EMAIL</label>
				    <form:input cssClass="form-control" id="email" path="email"/>
				     <div>
				    	<form:errors path="email"></form:errors>
				    </div>
				  </div>
				   <div class="mb-3">
				  	<label for="birth" class="form-label">BIRTH</label>
				    <form:input cssClass="form-control" id="birth" path="birth"/>
				    	  <div>
				    	<form:errors path="birth"></form:errors>
				    </div>
				  </div>
				  
				  <button type="submit" class="btn btn-primary">Submit</button>
				</form:form>
			</div>
		</div>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>
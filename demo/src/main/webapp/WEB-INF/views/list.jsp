<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="background-color: green; height: 400px; padding-top: 100px;" align="center" >
		<h1>list 페이지</h1>
		<div>
			<ul>
			<c:forEach items="${fruitList}" var="list" >
				<li>${list}</li>			
			</c:forEach>
			</ul>
		</div>
	</div>
</body>
</html>
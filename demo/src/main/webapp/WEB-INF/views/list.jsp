<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="background-color: yellow; height: 400px; padding-top: 100px;" align="center" >
		<h1>list 페이지</h1>
		<div>
			<ul>
			<c:forEach items="${fruitList}" var="list" >
				<li>${list}</li>			
			</c:forEach>
			</ul>
		</div>
	</div>
	<div style="background-color: yellow; height: 50px;  padding-top: 10px;" align="center" >
		<a href="/index"><button>메인 페이지이동</button></a>
		<a href="/login"><button>로그인 페이지이동</button></a>
		<a href="/list"><button>리스트 페이지이동</button></a>
	</div>	
</body>
</html>
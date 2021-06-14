<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>view02.jsp</h1>
	<c:forEach var="p" items="${people}">
		${p.name}<br>  
		${p.age}<br>
		${p.hobbies}<br>
	</c:forEach>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>quiz/view04.jsp</h1>
	제목 : ${board.title}<br>
	조회수 : ${board.hit}<br>
	작성일 : <fmt:formatDate value="${board.date}" pattern="yyyy-MM-dd" /><br>
</body>
</html>
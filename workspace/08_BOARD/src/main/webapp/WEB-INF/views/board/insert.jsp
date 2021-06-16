<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>게시글 작성 화면</h1>
	<form action="insertBoard.do">
		작성자<br>
		<input type="text" name="writer"><br><br>
		제목<br>
		<input type="text" name="title"><br><br>
		내용<br>
		<input type="text" name="content"><br><br>
		<button>작성완료</button>
	</form>
</body>
</html>
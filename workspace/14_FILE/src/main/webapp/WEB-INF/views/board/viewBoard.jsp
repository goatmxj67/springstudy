<%@page import="java.net.URLEncoder"%>
<%@page import="com.koreait.file.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<%
		Board board = (Board)request.getAttribute("board");  // model이 전달한 속성은 request에 있다.
		String encFilename = URLEncoder.encode(board.getFilename(), "utf-8");
		pageContext.setAttribute("encFilename", encFilename);
	%>

	<h1>게시글 보기 화면</h1>
	
	<form method="post">
		
		작성자<br>
		${board.writer}<br><br>
		
		제목<br>
		<input type="text" name="title" value="${board.title}"><br><br>
		
		내용<br>
		<input type="text" name="content" value="${board.content}"><br><br>
		
		첨부 이미지<br>
		<img alt="${board.filename}" src="resources/archive/${encFilename}" style="width: 300px;">
				
	</form>
</body>
</html>
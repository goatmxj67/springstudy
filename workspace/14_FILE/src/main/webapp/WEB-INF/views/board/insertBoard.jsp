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
	
	<form action="insertBoard.do"
		  method="post"
		  enctype="multipart/form-data">
	
		작성자<br>
		<input type="text" name="writer"><br><br>
		
		제목<br>
		<input type="text" name="title"><br><br>
		
		내용<br>
		<input type="text" name="content"><br><br>
		
		첨부<br>
		<input type="file" name="files" multiple><br><br>  <!-- multiple : 다중 첨부 가능 -->
	
		<button>저장하기</button>
	
	</form>
</body>
</html>
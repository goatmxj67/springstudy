<%@page import="java.net.URLEncoder"%>
<%@page import="com.koreait.file.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(function(){
			fn_update();
			fn_delete();
		});
		function fn_update(){
			$('#update_btn').click(function(){
				$('#f').attr('action', 'updateBoard.do');
				$('#f').submit();
			});
		}
		function fn_delete(){
			$('#delete_btn').click(function(){
				if (confirm('삭제할까요?')) {
					$('#f').attr('action', 'deleteBoard.do');
					$('#f').submit();
				}
			});
		}
	</script>
</head>
<body>

	<h1>게시글 보기 화면</h1>
	
	<form id="f" method="post" enctype="multipart/form-data">
	
		<input type="button" value="수정하기" id="update_btn">
		<input type="button" value="삭제하기" id="delete_btn"><br><br>

		<input type="hidden" name="no" value="${board.no}">
		<input type="hidden" name="filename1" value="${filename}">  <!-- 서버에 첨부된 첨부파일명 -->

		작성자<br>
		${board.writer}<br><br>
		
		제목<br>
		<input type="text" name="title" value="${board.title}"><br><br>
		
		내용<br>
		<input type="text" name="content" value="${board.content}"><br><br>
		
		첨부 변경<br>
		<input type="file" name="filename2"><br><br>
		
		첨부 이미지<br>
		<img alt="${filename}" src="resources/archive/${filename}" style="width: 300px;">
	
	</form>
	
</body>
</html>
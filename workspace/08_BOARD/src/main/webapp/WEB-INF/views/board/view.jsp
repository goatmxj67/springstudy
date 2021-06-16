<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			$('#update_page_btn').click(function(){
				$('#f').attr('action', 'updateBoardPage.do');
				$('#f').submit();
			})
			$('#delete_btn').click(function(){
				if (confirm('삭제할까요?')) {
					location.href = 'deleteBoard.do?no=${board.no}';
				}
			})
			$('#list_btn').click(function(){
				location.href = 'selectBoardList.do';
			})
		})
	</script>
</head>
<body>
	
	<h1>${board.no}번 게시글</h1>
	작성자: ${board.writer}<br>
	제목: ${board.title}<br>
	내용: ${board.content}<br>
	작성일: ${board.postdate}<br><br>
	
	<form id="f" method="post">
		<input type="hidden" name="no" value="${board.no}">
		<input type="hidden" name="title" value="${board.title}">
		<input type="hidden" name="content" value="${board.content}">
		<input type="button" value="수정하러가기" id="update_page_btn">
		<input type="button" value="삭제하기" id="delete_btn">
		<input type="button" value="목록으로이동" id="list_btn">
	</form>
	
</body>
</html>
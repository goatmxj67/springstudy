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
			$('#f').submit(function(event){
				if ('${board.title}' == $('#title').val() && '${board.content}' == $('#content').val()) {
					alert('수정할 내용이 없습니다.');
					event.preventDefault();
					return false;
				}
			})
			$('#list_btn').click(function(){
				location.href = 'selectBoardList.do';
			})
		})
	</script>
</head>
<body>

	<h1>${board.no}번 게시글 수정 페이지</h1>
	<form id="f" action="updateBoard.do" method="post">
		제목<br>
		<input type="text" name="title" id="title" value="${board.title}"><br><br>
		내용<br>
		<input type="text" name="content" id="content" value="${board.content}"><br><br>
		<input type="hidden" name="no" value="${board.no}">
		<button>수정하기</button>
		<input type="button" value="목록으로이동" id="list_btn">
	</form>
	
</body>
</html>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.koreait.mine.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style>
		.reply_form {
		width: 100%;
	}
	.reply_form textarea {
		width: 85%;
		height: 50px;
	}
	.reply_form button {
		width: 13%;
	}
	.reply_list table {
		width: 100%;
		border-collapse: collapse;
		border-top: 1px solid gray;
		border-bottom: 1px solid gray;
	}
	.reply_list table td {
		padding: 10px;
		border-bottom: 1px solid gray;
	}
	.reply_list table td:nth-of-type(1) { width: 70%; }
	.reply_list table td:nth-of-type(2) { width: 10%; }
	.reply_list table td:nth-of-type(3) { width: 15%; }
	.reply_list table td:nth-of-type(4) { width: 5%; }
	</style>
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
	
	<%-- 댓글 입력창 --%>
<div class="reply_form">
	<form action="insertReply.do" method="post">
		<input type="hidden" name="boardIdx" value="${board.no}"> 
		<textarea name="content" placeholder="로그인을 하면 작성할 수 있습니다."></textarea>
		<c:if test="${loginUser != null}">
			<button>작성하기</button>
		</c:if>
	</form>
</div>

<%-- 댓글 목록창 --%>
<div class="reply_list">
	댓글 ${replyCount}개<br>
	<table>
		<tbody>
			<c:forEach var="replyDTO" items="${replyList}">
				<tr>
					<td>${replyDTO.content}</td>
					<td>${replyDTO.author}</td>
					<td>${replyDTO.postdate}</td>
					<td>
						<c:if test="${loginUser.id == replyDTO.author}">  <%-- 댓글의 작성자만 삭제할 수 있다. --%>
							<a href="deleteReply.do?replyIdx=${replyDTO.idx}&idx=${replyDTO.boardIdx}">삭제</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
	
</body>
</html>
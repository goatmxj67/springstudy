<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="com.koreait.file.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			fn_selectList();
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
		function fn_selectList(){
			$('#selectList_btn').click(function(){
				location.href = 'selectBoardList.do';
			})
		}
	</script>
</head>
<body>

	<h1>게시글 보기 화면</h1>
	
	<form id="f" method="post" enctype="multipart/form-data">
	
		<input type="button" value="수정하기" id="update_btn">
		<input type="button" value="삭제하기" id="delete_btn">
		<input type="button" value="목록보기" id="selectList_btn"><br><br>

		<input type="hidden" name="no" value="${board.no}">
		<input type="hidden" name="origin_filename" value="${board.origin_filename}">
		<input type="hidden" name="save_filename" value="${board.save_filename}">

		작성자<br>
		${board.writer}<br><br>
		
		작성일<br>
		${board.postdate}<br><br>

		작성IP<br>
		${board.ip}<br><br>

		제목<br>
		<input type="text" name="title" value="${board.title}"><br><br>
		
		내용<br>
		<input type="text" name="content" value="${board.content}"><br><br>
		
		첨부 변경<br>
		<input type="file" name="new_file"><br>
		
		<c:if test="${not empty board.origin_filename}">
			기존에 ${board.origin_filename} 파일이 첨부되어 있습니다.<br><br>
			첨부 이미지<br>
			<img alt="${board.origin_filename}" src="resources/archive/${board.save_filename}" style="width: 300px;"><br>
			<a href="download.do?no=${board.no}">다운로드</a>
		</c:if>
	
	</form>
	
</body>
</html>

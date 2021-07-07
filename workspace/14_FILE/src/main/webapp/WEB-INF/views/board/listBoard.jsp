<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
	<div data-page="1"></div>
	<div data-page="2"></div>
	<div data-page="3"></div>
	<div data-page="4"></div>
	<div data-page="5"></div>
	<script>
		const divList = $('div[data-page]');
		$.each(divList, function(i, div) {
			console.log($(div).data('page'));
		});
	</script>
	<h1>게시글 목록 보기</h1>
	
	<a href="insertBoardPage.do">새 글 작성하러 가기</a><br><br>
	
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>첨부</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5">없음</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.no}</td>
						<td><a href="selectBoardByNo.do?no=${board.no}">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.postdate}</td>
						<td>
							<c:if test="${not empty board.save_filename}">
								<a href="download.do?no=${board.no}"><i class="fas fa-paperclip"></i></a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	
</body>
</html>
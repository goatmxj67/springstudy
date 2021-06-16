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
		$(document).ready(function() {
			
			$('#insert_btn').click(function(){
				location.href = 'insertBoardPage.do';
			})
			
		})
	</script>
	<style>
		table {
			width: 500px;
			border-collapse: collapse;
		}
		td {
			padding: 5px;
			text-align: center;
			border-top: 1px solid gray;
			border-bottom: 1px solid gray;
		}
		td:nth-of-type(1) { width: 100px; }
		td:nth-of-type(2) { width: 100px; }
		td:nth-of-type(3) { width: 200px; }
		td:nth-of-type(4) { width: 100px; }
	</style>
</head>
<body>
	<h1>게시판 목록</h1>
	<table>
		<thead>
			<tr>
				<td>게시글번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="4">작성된 게시글이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.no}</td>
						<td>${board.writer}</td>				
						<td>${board.title}</td>
						<td>${board.postdate}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<input type="button" value="새글작성하러가기" id="insert_btn">
				</td>
			</tr>
		</tfoot>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	총 게시글 : ${totalBoard}개<br/>
	<table border="1">
		<thead>
			<tr>
				<td>순번</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${empty list}">
				<tr>
					<td colspan="4">게시물이 없습니다.</td>
				</tr>
			</c:if>
			<c:if test="${not empty list}">
				<c:forEach var="boardDTO" items="${list}">
					<tr>
						<td>${boardDTO.bIdx}</td>
						<td>${boardDTO.bWriter}</td>
						<td><a href="selectBoardByIdx.do?bIdx=${boardDTO.bIdx}">${boardDTO.bTitle}</a></td>
						<td>${boardDTO.bDate}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4"><input type="button" value="새글작성" onclick="location.href='insertPage.do'"/></td>
			</tr>
		</tfoot>
	</table>

</body>
</html>
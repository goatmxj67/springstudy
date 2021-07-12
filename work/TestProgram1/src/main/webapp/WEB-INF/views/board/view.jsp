<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function fn_update(f) {
			f.action = 'updateBoard.do';
			f.submit();
		}
		function fn_delete(f) {
			if (confirm('정말 삭제하시겠습니까 ?')) {
				f.action = 'deleteBoard.do';
				f.submit();
			}
		}
	</script>
</head>
<body>
	
	<form>
		<table border="1">
			<tr>
				<td>순번</td>
				<td>${boardDTO.bIdx}</td>
			</tr>
			<tr>
				<td>작성자</td>
				<td>${boardDTO.bWriter}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="bTitle" value="${boardDTO.bTitle}">
			</td>
			<tr>
				<td>내용</td>
				<td><textarea rows="10" cols="50" name="bContent">${boardDTO.bContent}</textarea></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="button" value="수정" onclick="fn_update(this.form)">
					<input type="button" value="목록" onclick="location.href='selectBoardList.do'">
					<input type="button" value="삭제" onclick="fn_delete(this.form)">
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>
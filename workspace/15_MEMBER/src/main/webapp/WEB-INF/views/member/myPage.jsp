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
			fn_update();
		});
		function fn_update() {
			$('#update_btn').click(function(){
				if (confirm('수정할까요?')) {
					$('#f').attr('action', 'updateMember.do');			
					$('#f').submit();
				}
			});
		}
	</script>
</head>
<body>
	
	<h1>마이 페이지</h1>
	
	<form id="f" method="post">
	
		회원번호<br>
		${loginUser.no}<br><br>
		
		아이디<br>
		${loginUser.id}<br><br>
		
		가입일시<br>
		${loginUser.regdate}<br><br>
		
		현재 비밀번호<br>
		<input type="password" name="pw0" id="pw0"><br>
		새 비밀번호<br>
		<input type="password" name="pw" id="pw"><br>
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2"><br><br>
		<input type="button" value="비밀번호변경하기" id="pw_btn"><br><br>
		
		이름<br>
		<input type="text" name="name" value="${loginUser.name}"><br><br>
		
		이메일<br>
		<input type="text" name="email" value="${loginUser.email}"><br><br>
		
		<input type="hidden" name="no" value="${loginUser.no}">
		
		<input type="button" value="정보변경하기" id="update_btn">
		<input type="button" value="돌아가기" onclick="location.href='/'">
		
	</form>
	
</body>
</html>
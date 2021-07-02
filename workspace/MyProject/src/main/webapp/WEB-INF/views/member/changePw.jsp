<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
		<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				fn_changePw();
			});
			function fn_changePw() {
				$('#change_pw_btn').click(function(){
					var regPW = /^[0-9]{1,4}$/;
					if ( !regPW.test($('#pw').val()) ) {
						alert('비밀번호는 ~~~입니다.');
						$('#pw').focus();
						return false;
					} else if ($('#pw').val() != $('#pw2').val()) {
						alert('비밀번호를 확인하세요.');
						return false;
					} else {
						$('#f').attr('action', 'changePw.do');
						$('#f').submit();
					}
				});
			}
		</script>
</head>
<body>
	
	<h1>비밀번호 변경</h1>
	
	<form id="f" method="post">
		<input type="hidden" name="email" value="${email}">
		새 비밀번호<br>
		<input type="password" name="pw" id="pw"><br><br>
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2"><br><br>
		<input type="button" value="비밀번호 변경" id="change_pw_btn">
		<input type="button" value="돌아가기" onclick="location.href='index.do'">
	</form>
	
</body>
</html>
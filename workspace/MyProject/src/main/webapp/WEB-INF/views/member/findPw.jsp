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
			fn_verify_num();
			
			document.getElementById('refresh_btn').addEventListener('click', function(){
				location.href = 'getCaptchaKey.do';
			})
			
		})
		function fn_verify_num(){
			$('#verify_num_btn').click(function(){
				if ($('#email').val() == '') {
					alert('이메일을 입력하세요');
					$('#email').focus();
					return false;
				}
				$.ajax({
					url: 'verifyNum.do',
					type: 'get',
					data: 'email=' + $('#email').val(),
					dataType: 'json',
					success: function(resultMap) {
						alert('인증코드가 발송되었습니다.');
						fn_verify(resultMap.authCode);
					}
				});
			});
		}
		function fn_verify(authCode) {
			$('#verify_btn').click(function() {
				if (authCode == $('#user_key').val()) {
					alert('인증되었습니다.');
				} else {
					alert('인증이 실패했습니다.');
					history.back();
				}
			});
		}
	</script>	
</head>
<body>

	<h1>비밀번호 찾기</h1>
	
	<form id="f" action="loginValidation.do" method="get">
		아이디<br>
		<input type="text" id="id" name="name"><br><br>
		가입 당시 이메일을 입력하세요<br>
		<input type="text" name="email" id="email">
		<input type="button" value="인증번호받기" id="verify_num_btn"><br>
		<input type="text" name="user_key" id="user_key">
		<input type="button" value="인증하기" id="verify_btn"><br><br>	
		<p>자동가입 방지문자</p>
		<img src="${DIR}/${filename}" style="width: 200px;">
		<input type="button" value="새로고침" id="refresh_btn"><br>
		<input type="text" name="user_key1" placeholder="입력하세요">
		
		<button>다음</button>
	</form>

</body>
</html>
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
			fn_join();
			fn_verify_num();
		});
		var authCode;
		function fn_verify(){
			$('#verify_btn').click(function(){
				if(authCode == $('#user_key').val()){
					alert('인증되었습니다.');
				} else {
					alert('인증에 실패했습니다.');
				}
			});
		}
		function fn_verify_num(){
			$('#verify_num_btn').click(function(){
				$.ajax({
					url: 'verifyNum.do',
					type: 'get',
					data: 'email=' + $('#email').val(),
					dataType: 'json',
					success: function(resultMap){
						authCode = resultMap.authCode;
						fn_verify();
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				})
			});
		}
		function fn_join(){
			$('#join_btn').click(function(){
				fn_idCheck();
			});
		}
		function fn_idCheck() {
			$.ajax({
				url: 'idCheck.do',
				type: 'get',
				data: 'id=' + $('#id').val(),
				dataType: 'json',
				success: function(res){
					if (res.count == 0) {
						alert('가입 가능한 아이디입니다.');
						$('#f').attr('action', 'join.do');
						$('#f').submit();
					} else {
						alert('가입된 아이디입니다.');
					}
				},
				error: function(xhr, textStatus, errorThrown) {
					
				}
			});
		}
	</script>
</head>
<body>

	<h1>회원가입</h1>
	
	<form id="f" method="post">
		
		아이디<br>
		<input type="text" name="id" id="id"><br><br>
		
		비밀번호<br>
		<input type="password" name="pw" id="pw"><br><br>
		
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2"><br><br>
		
		이름<br>
		<input type="text" name="name" id="name"><br><br>
		
		이메일<br>
		<input type="text" name="email" id="email">
		<input type="button" value="인증번호받기" id="verify_num_btn"><br>
		<input type="text" name="user_key" id="user_key">
		<input type="button" value="인증하기" id="verify_btn"><br><br>
		
		<input type="button" value="가입하기" id="join_btn">
		
	</form>

</body>
</html>
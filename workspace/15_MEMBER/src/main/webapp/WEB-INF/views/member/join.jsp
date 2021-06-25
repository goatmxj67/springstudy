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
			fn_idCheck();
			fn_pwCheck();
			fn_pwCheck2();
			fn_verify_num();
			fn_join();
		});
		// 아이디 중복체크 함수
		var idPass = false;  // 아이디 중복체크 통과여부
		function fn_idCheck() {
			$('#id').keyup(function(){  // keyup : 아이디를 입력할때마다
				var regID = /^[a-z]{1,5}$/;  // 나중에 수정해서 사용
				if (!regID.test($('#id').val())) {
					$('#id_result').text('아이디는 ~~~입니다.').css('color', 'red');
					return false;
				}
				$.ajax({
					url: 'idCheck.do',
					type: 'get',
					data: 'id=' + $('#id').val(),
					dataType: 'json',
					success: function(res){
						if (res.count == 0) {
							$('#id_result').text('가입 가능한 아이디입니다.').css('color', 'blue');
							idPass = true;
						} else {
							$('#id_result').text('이미 사용 중인 아이디입니다.').css('color', 'red');
							idPass = false;
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			})
		}
		// 비밀번호 검증 함수
		var pwPass = false;
		function fn_pwCheck(){
			$('#pw').keyup(function(){  // keyup : 비밀번호를 입력할때마다
				var regPW = /^[0-9]{1,4}$/;  // 나중에 수정해서 사용
				if (regPW.test($('#pw').val())){
					$('#pw_result').text('사용 가능한 비밀번호입니다.').css('color', 'blue');
					pwPass = true;
				} else {
					$('#pw_result').text('비밀번호는 ~~~입니다.').css('color', 'red');
					pwPass = false;
				}
			});
		}
		// 비밀번호 입력 확인 함수
		var pwPass2 = false;
		function fn_pwCheck2(){
			$('#pw2').keyup(function(){  // keyup : 비밀번호를 입력할때마다
				if ($('#pw').val() == $('#pw2').val()){
					$('#pw2_result').text('비밀번호 일치').css('color', 'blue');
					pwPass2 = true;
				} else {
					$('#pw2_result').text('비밀번호를 확인하세요.').css('color', 'red');
					pwPass2 = false;
				}
			});
		}
		// 이메일 인증번호 받기 함수
		function fn_verify_num(){
			$('#verify_num_btn').click(function(){
				if ($('#email').val() == '') {
					alert('이메일을 입력하세요.');
					$('#email').focus();
					return false;
				}
				$.ajax({
					url: 'verifyNum.do',
					type: 'get',
					data: 'email=' + $('#email').val(),
					dataType: 'json',
					success: function(resultMap){
						alert('인증코드가 발송되었습니다.');
						fn_verify(resultMap.authCode);  // 인증번호 검증 함수로 발행된 인증코드 전달
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				})
			});
		}
		// 이메일 인증번호 검증 함수
		var authPass = false;  // 이메일 인증 통과여부
		function fn_verify(authCode){
			$('#verify_btn').click(function(){
				if(authCode == $('#user_key').val()){
					alert('인증되었습니다.');
					authPass = true;
				} else {
					alert('인증에 실패했습니다.');
					authPass = false;
				}
			});
		}
		// 회원가입함수
		function fn_join(){
			$('#join_btn').click(function(){
				if ( !idPass ) {
					alert('아이디를 확인하세요.');
					return false;
				} else if ( !pwPass || !pwPass2 ) {
					alert('비밀번호를 확인하세요.');
					return false;
				} else if ( !authPass ) {
					alert('이메일 인증을 받아야 합니다.');
					return false;
				} else {
					$('#f').attr('action', 'join.do');
					$('#f').submit();
				}
			});
		}
	</script>
</head>
<body>

	<h1>회원가입</h1>
	
	<form id="f" method="post">
		
		아이디<br>
		<input type="text" name="id" id="id">
		<span id="id_result"></span><br><br>
		
		비밀번호<br>
		<input type="password" name="pw" id="pw">
		<span id="pw_result"></span><br><br>
		
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2">
		<span id="pw2_result"></span><br><br>
		
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
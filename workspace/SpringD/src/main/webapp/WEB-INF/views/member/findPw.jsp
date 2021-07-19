<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 찾기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		// 페이지 로드
		$(document).ready(function(){
			fn_emailCheck();
			fn_email_code();
		})
		// 이메일 확인(emailCheck) : 가입할 때 사용했던 이메일 중복 체크 재사용
		var emailPass = false;
		function fn_emailCheck(){
			$('#mEmail').keyup(function(){
				$.ajax({
					url: 'emailCheck.do',
					type: 'get',
					data: 'mEmail=' + $('#mEmail').val(),
					dataType: 'json',
					success: function(res){
						if(res.result == 0){ // 일치하는 이메일이 없다면
							$('.email_result').text('등록되지 않은 이메일입니다. 이메일 주소를 확인하세요.');
							$('#email_code_btn').click(function(){ }).prop("disabled", true); // 이메일 인증코드 버튼 비활성화
								emailPass = false;
						} else{ // 일치하는 이메일이 있다면
							$('.email_result').text('이메일이 확인되었습니다. 인증번호를 받으세요.');
							$('#email_code_btn').click(function(){ }).prop("disabled", false); // 이메일 인증코드 버튼 활성화
								emailPass = true;
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			})
		}
		// 이메일 인증코드 받기(emailCode)
		function fn_email_code(){
			$('#email_code_btn').click(function(){
				if ($('#mId').val() == '' || $('#mEmail').val() == '') { // 아이디, 이메일 중 하나라도 입력 값이 없다면
					alert('아이디와 이메일 모두 입력하세요.');
					return false;
				}
				$.ajax({
					url: 'emailCode.do',
					type: 'get',
					data: 'mEmail=' + $('#mEmail').val(),
					dataType: 'json',
					success: function(resultMap) {
						alert('인증코드가 발송되었습니다.');
						fn_email_auth(resultMap.authCode);
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			});
		}
		// 이메일 인증(emailAuth)
		function fn_email_auth(authCode) {
			$('#email_auth_btn').click(function() {
				if(authCode == $('#user_key').val()) {
					alert('인증되었습니다. 비밀번호 변경 페이지로 이동합니다.');
					$('#f').attr('action', 'changePwPage.do');
					$('#f').submit();
				} else{
					alert('인증에 실패했습니다. 인증코드를 정확히 입력하세요.');
					history.back();
				}
			});
		}
	</script>
</head>
<body>

	<h3>비밀번호 찾기</h3>
	
	<form id="f" method="post">
		가입 당시 이메일을 입력하세요.<br><br>
		<input type="text" name="mEmail" id="mEmail" placeholder="Email"><br>
		<span class="email_result"></span><br><br>
		<input type="button" value="인증번호 받기" id="email_code_btn"><br>
		<input type="text" name="user_key" id="user_key">
		<input type="button" value="인증하기" id="email_auth_btn"><br><br>
		<span class="emailAuth_result"></span><br>
		<input type="button" value="돌아가기" onclick="location.href='index.do'">
	</form>

</body>
</html>
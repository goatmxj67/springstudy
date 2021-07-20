<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>마이페이지</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		// 페이지 로드
		$(document).ready(function(){
			fn_updatePw();
			fn_presentPwCheck();
			fn_updateMember();
			fn_emailCheck();
			fn_email_code();
			fn_leave();
		});
		// 현재 비밀번호 확인(presentPwCheck)
		var presentPwPass = false;
		function fn_presentPwCheck(){
			$('#mPw0').keyup(function(){
				var obj = {
					pw: $('#mPw0').val()
				};
				$.ajax({
					url: 'presentPwCheck.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					dataType: 'json',
					success: function(resultMap){
						console.log(resultMap.isCorrect);
						if (resultMap.isCorrect) {
							presentPwPass = true;
						} else {
							presentPwPass = false;
						}
					}
				});
			});
		}
		// 비밀번호 변경(updatePw)
		function fn_updatePw(){
			$('#pw_btn').click(function(){
				if($('#mPw0').val() == ''){ // 현재 비밀번호를 입력하지 않을 경우
					alert('현재 비밀번호를 입력하세요.');
					$('#mPw0').focus();
					return false;
				} else if(!presentPwPass){ // 위 현재 비밀번호 통과를 안 했을 경우(기존 비밀번호와 일치하지 않을 경우)
					alert('현재 비밀번호가 일치하지 않습니다. 확인해주세요.');
					$('#mPw0').focus();
					return false;
				} else if($('#mPw').val() == ''){ // 새로운 비밀번호를 입력하지 않을 경우
					alert('새 비밀번호를  입력하세요.');
					$('#mPw').focus();
					return false;
				} else if($('#mPw').val() != $('#mPw1').val()){ // 새로 입력한 비밀번호와 확인 검증이 이뤄지지 않을 경우
					alert('새로 입력한 비밀번호가 일치하지 않습니다. 확인해주세요.');
					return false;
				} else{
					$('#f').attr('action', 'updatePw.do');
					$('#f').submit();
				}
			});
		}
		// 이메일 중복 체크(emailCheck)
		var emailPass = false;
		function fn_emailCheck(){
			// 이메일 인증코드 버튼 비활성화(이메일을 변경하지 않을 경우 인증절차를 막기 위함)
			$('#email_code_btn').click(function(){ }).prop("disabled", true); 
			$('#mEmail').keyup(function(){
				$.ajax({
					url: 'emailCheck.do',
					type: 'get',
					data: 'mEmail=' + $('#mEmail').val(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.result == 0){ // DB에 일치하는 email이 없는 경우 인증버튼 활성화
							$('#email_code_btn').click(function(){ }).prop("disabled", false); // 이메일 인증코드 버튼 활성화
							$('.email_result').text('사용 가능한 이메일입니다. 인증코드를 받으세요.');
							emailPass = true;
						} else{ // DB에 일치하는 email이 있는 경우 인증버튼 비활성화로 막기
							$('.email_result').text('이미 사용 중인 이메일입니다. 이메일 주소를 확인하세요.');
							$('#email_code_btn').click(function(){ }).prop("disabled", true);
							emailPass = false;
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			});
		}
		// 이메일 인증코드 받기(emailCode)
		function fn_email_code(){
			$('#email_code_btn').click(function(){
				if($('#mEmail').val() == ''){ // 이메일을 입력하지 않을 경우
					alert('수정할 이메일을 입력하세요.');
					$('#mEmail').focus();
					return false;
				} else if($('#mEmail').val() == '${loginUser.MEmail}'){
					alert('이미 인증된 이메일입니다.');
					return false;
				}
				$.ajax({
					url: 'emailCode.do',
					type: 'get',
					data: 'mEmail=' + $('#mEmail').val(),
					dataType: 'json',
					success: function(resultMap){
						alert('인증코드가 발송되었습니다. 메일을 확인하세요.');
						fn_email_auth(resultMap.authCode);
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			});
		}
		// 이메일 인증(emailAuth)
		var authPass = false;
		function fn_email_auth(authCode){
			$('#email_auth_btn').click(function(){
				if(authCode == $('#user_key').val()){ // 받은 인증코드와 입력된 값이 같을 경우
					$('.emailAuth_result').text('인증되었습니다.');
					authPass = true;
				} else{
					$('.emailAuth_result').text('인증에 실패했습니다. 다시 시도해주세요.');
					$('#mEmail').val() = '';
					authPass = false;
				}
			});
		}
		// 회원 정보 변경(updateMember)
		function fn_updateMember(){
			$('#update_btn').click(function(){
				// 이름, 전화번호, 이메일 중 하나라도 공백일 경우
				if($('#mName').val() == '' 
				   || $('#mPhone').val() == ''
				   || $('#mEmail').val() == ''){ 
					  alert('이름, 전화번호, 이메일은 필수정보 입니다. 내용을 입력하세요.');
					  return false;
				}
				// 기존에 값과 동일할 경우(하나라도 변경 하지 않았을 경우)
				if($('#mName').val() == '${loginUser.MName}'
			   	 	  && $('#mPhone').val() == '${loginUser.MPhone}'
				      && $('#mEmail').val() == '${loginUser.MEmail}'){ 
				      alert('변경된 정보가 없습니다.');
				      return false;
				}
				// 하나라도 변경된 정보가 있을 경우 회원정보 변경 성공(단, 이메일 변경 시에는 인증 필수)
				if($('#mName').val() != '${loginUser.MName}'
		   		   || $('#mPhone').val() != '${loginUser.MPhone}'
		   		   || $('#mEmail').val() != '${loginUser.MEmail}'){ 
						// 그중 이메일이 변경됐는데 이메일 인증을 안 했을 경우(회원정보 변경 불가)
						if($('#mEmail').val() != '${loginUser.MEmail}' && !authPass){ 
							alert('이메일 변경 시, 이메일인증은 필수입니다.');
							return false;
					  	}
						$('#f').attr('action', 'updateMember.do');
						$('#f').submit();
				}
			});
		}
		// 회원 탈퇴(leave)
		function fn_leave(){
			$('#leave_btn').click(function(){
				if (confirm('정말 탈퇴하시겠습니까?')) {
					location.href = 'leave.do?mNo=${loginUser.MNo}';		
				} else{
					alert('비밀번호를 확인하세요.');
				}
			});
		}
	</script>
</head>
<body>
	
	<!-- 마이페이지 화면 -->
	<h3>${loginUser.MId} 님 환영합니다!</h3>
	<form id="f" method="post">
	
		이름<br>
		<input type="text" name="mName" id="mName" value="${loginUser.MName}"><br><br>
		
		아이디<span>(아이디는 수정 불가)</span><br>
		<input type="text" name="mId" id="mId" value="${loginUser.MId}" readonly><br><br>
		
		현재 비밀번호<br>
		<input type="text" name="mPw0" id="mPw0" value="${loginUser.MPw}"><br><br>
		새 비밀번호<br>
		<input type="password" name="mPw" id="mPw"><br><br>
		새 비밀번호 확인<br>
		<input type="password" name="mPw1" id="mPw1"><br><br>
		<input type="button" id="pw_btn" value="비밀번호 변경하기"><br><br>
		
		전화번호<br>
		<input type="text" name="mPhone" id="mPhone" value="${loginUser.MPhone}"><br><br>
		
		이메일<br>
		<input type="text" name="mEmail" id="mEmail" value="${loginUser.MEmail}"><br>
		<span class="email_result"></span><br>
		<input type="button" id="email_code_btn" value="인증코드 받기"><br>
		<input type="text" name="user_key" id="user_key">
		<input type="button" id="email_auth_btn" value="인증하기"><br>
		<span class="emailAuth_result"></span><br><br>
			
		가입일 : ${loginUser.MRegdate}<br><br>
		
		<input type="hidden" name="mNo" value="${loginUser.MNo}">
		<input type="button" id="update_btn" value="정보변경하기">
		<input type="button" id="leave_btn" value="회원탈퇴">
		<input type="button" value="돌아가기" onclick="location.href='loginPage.do'">
	</form>
	
</body>
</html>
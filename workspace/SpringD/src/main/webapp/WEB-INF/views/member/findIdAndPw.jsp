<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디/비밀번호 찾기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		// 페이지 로드
		$(document).ready(function(){
			fn_findCheck();
			fn_findId();
			fn_findPw();
			fn_emailCheck();
		})
		// 아이디/비밀번호 찾기 radio 체크에 따른 화면 전환
		function fn_findCheck(){
			$('input[type=radio][name=find]').on('click', function(){
				var chkValue = $('input[type=radio][name=find]:checked').val();
				if(chkValue == '1'){
					$('#findId_form').css('display', 'block');
					$('#findPw_form').css('display', 'none');
				} else if(chkValue == '2'){
					$('#findId_form').css('display', 'none');
					$('#findPw_form').css('display', 'block');
				}
			});
		}
		// 아이디 찾기(findId)
		function fn_findId(){
			$('#findId_btn').click(function(){
				if($('#mName').val() == '' || $('#mEmail').val() == ''){ // 이름, 이메일 중 하나라도 입력 값이 없다면
					alert('이름과 이메일 모두 입력하세요');
					return false;
				} 
				// 이름과 이메일 객체 생성(post 방식) : presentPwCheck와 마찬가지로 jackson lib 오류
				/*var obj = { 
						mName : $('#mName').val(),
						mEmail : $('#mEmail').val()
				}; */
				$.ajax({
					url: 'findId.do',
					type: 'get',
					// data: JSON.stringify(obj),
					data: 'mName=' + $('#mName').val() + '&mEmail=' + $('#mEmail').val(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.status == 500){ // 이름과 이메일이 일치하지 않을 경우
							alert('입력하신 정보와 일치하는 계정이 없습니다.');
							$('#mName').val('');
							$('#mEmail').val('');
							return false;
						} else if(resultMap.status == 200){ // 이름과 이메일이 일치할 경우 mId 반환
							alert("회원님이 찾으시는 아이디는 " + resultMap.mId + " 입니다.");
							$('#mName').val('');
							$('#mEmail').val('');
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			});
		}
		// 비밀번호 찾기(findPw) : 이메일 인증 필요(mEmail2)
		function fn_findPw(){
			// 이메일 인증코드 버튼 비활성화(아이디, 이메일 확인 전까지 인증절차를 막기 위함)
			$('#emailCode_btn').click(function(){ }).prop("disabled", true); 
			$('#findPw_btn').click(function(){
				if($('#mId').val() == '' || $('#mEmail2').val() == ''){
					alert('아이디와 이메일 모두 입력하세요');
					return false;
				}
				$.ajax({
					url: 'findPw.do',
					type: 'get',
					data: 'mId=' + $('#mId').val() + '&mEmail=' + $('#mEmail2').val(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.status == 500){ // 아이디와 이메일이 일치하지 않을 경우
							$('.email2_result').text('입력하신 아이디와 이메일이 일치하지 않습니다. 확인하세요.');
							$('#mId').val('');
							$('#mEmail2').val('');
							return false;
						} else if(resultMap.status == 200){ // 아이디와 이메일이 일치할 경우 인증코드 버튼 활성화
							$('#emailCode_btn').click(function(){ }).prop("disabled", false);
							$('.email2_result').text("입력하신 정보가 확인되었습니다. 인증코드를 받으세요.");
							fn_emailCode();
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			});
		}
		// 이메일 인증코드 받기(emailCode)
		function fn_emailCode(){
			$('#emailCode_btn').click(function(){
				$.ajax({
					url: 'emailCode.do',
					type: 'get',
					data: 'mEmail=' + $('#mEmail2').val(),
					dataType: 'json',
					success: function(resultMap) {
						alert('인증코드가 발송되었습니다.');
						fn_emailAuth(resultMap.authCode);
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			});
		}
		// 이메일 인증(emailAuth)
		function fn_emailAuth(authCode) {
			$('#emailAuth_btn').click(function() {
				if(authCode == $('#userKey').val()) { // 받은 인증코드와 입력된 값이 같을 경우
					alert('인증되었습니다. 비밀번호 변경 페이지로 이동합니다.');
					$('#f2').attr('action', 'changePwPage.do');
					$('#f2').submit();
				} else{
					alert('인증에 실패했습니다. 인증코드를 정확히 입력하세요.');
					history.back();
				}
			});
		}
		// 이메일 정규식(emailCheck)
		function fn_emailCheck(){
			$('#mEmail').keyup(function(){
				// 이메일 정규식
				var regEmail = /^[a-z0-9][a-z0-9_-]*@[a-zA-Z0-9]+([.][a-zA-Z]{2,}){1,2}$/;
				if(!regEmail.test($('#mEmail').val())){ // 이메일 정규식 조건을 통과하지 못 했을 경우
					$('.email_result').text('이메일 형식에 맞지 않습니다. 다시 입력하세요.');
					return false;
				} else{
					$('.email_result').text('');
					return true;
				}
			});
			
			$('#mEmail2').keyup(function(){
				// 이메일 정규식
				var regEmail = /^[a-z0-9][a-z0-9_-]*@[a-zA-Z0-9]+([.][a-zA-Z]{2,}){1,2}$/;
				if(!regEmail.test($('#mEmail2').val())){ // 이메일 정규식 조건을 통과하지 못 했을 경우
					$('.email2_result').text('이메일 형식에 맞지 않습니다. 다시 입력하세요.');
					return false;
				} else{
					$('.email2_result').text('');
					return true;
				}
			});
		}
	</script>
</head>
<body>

	<h3>아이디/비밀번호 찾기</h3>
	
	<div class="find_form">
		<!-- 아이디 찾기 -->
		<input type="radio" name="find" id="findId" value="1" checked="checked">
		<label for="findId">아이디 찾기</label>
		<!-- 비밀번호 찾기 -->
		<input type="radio" name="find" id="findPw" value="2">
		<label for="findPw">비밀번호 찾기</label>
	</div>
	<hr>
	
	<!-- 아이디 찾기 화면 -->
	<div id="findId_form">
		<p>가입 당시 이름과 이메일을 입력하세요.</p>
		<form id="f1">
			<!-- 이름 -->
			<div class="form_group">
				<label for="mName">이름</label><br>
				<input type="text" name="mName" id="mName" placeholder="ex) 홍길동">
			</div><br>
			<!-- 이메일 -->
			<div class="form_group">
				<label for="mEmail">이메일</label><br>
				<input type="text" name="mEmail" id="mEmail" placeholder="ex) hong@example.com"><br>
				<span class="email_result"></span><br>
			</div><br>
			<!-- 확인 버튼 -->
			<div class="form_group">
				<input type="button" id="findId_btn" value="확인">
			</div>
		</form>
	</div>
	<br><br>
	
	<!-- 비밀번호 찾기 화면 -->
	<div id="findPw_form" style="display: none;"> <!-- 처음에는 보이지 않고 비번찾기 시에 화면 나타내기 -->
		<p>가입 당시 아이디와 이메일을 입력하세요.(이메일 인증 필요)</p>
		<form id="f2" method="post">
			<!-- 아이디 -->
			<div class="form_group">
				<label for="mId">아이디</label><br>
				<input type="text" name="mId" id="mId" placeholder="ex) hong123">
			</div><br>
			<!-- 이메일 -->
			<div class="form_group">
				<label for="mEmail2">이메일</label><br>
				<input type="text" name="mEmail" id="mEmail2" placeholder="ex) hong@example.com"><br>
				<span class="email2_result"></span><br>
			</div><br>
			<!-- 확인 버튼 -->
			<div class="form_group">
				<input type="button" id="findPw_btn" value="확인">
			</div><br><br>
		</form>
		
		<form id="f3">
			<input type="button" id="emailCode_btn" value="인증코드 받기"><br>
			<input type="text" name="userKey" id="userKey">
			<input type="button" id="emailAuth_btn" value="인증하기"><br><br>
			<span class="emailAuth_result"></span><br>
		</form>
	</div>
	<br><br>
	
	<input type="button" value="로그인" onclick="location.href='index.do'">
	<input type="button" value="회원가입" onclick="location.href='joinPage.do'">

</body>
</html>
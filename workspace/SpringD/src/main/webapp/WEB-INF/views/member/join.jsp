<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		// 페이지 로드
		$(document).ready(function(){
			fn_idCheck();
			fn_pwCheck();
			fn_pwCheck2();
			fn_emailCheck();
			fn_emailCode();
			fn_phoneCheck();
			fn_join();
		});
		// 아이디 중복체크(idCheck)
		var idPass = false;
		function fn_idCheck(){
			$('#mId').keyup(function(){
				// 아이디 정규식
				var regId = /^[a-z]{3,6}$/;
				if(!regId.test($('#mId').val())){ // 아이디 정규식 조건을 통과하지 못 했을 경우
					$('.id_result').text('아이디는 영어 소문자 3~6자리만 입력 가능합니다.');
					// 실제 사용할 ID 정규식
					// ^[a-z][a-z0-9_-]{4,19}$
					// 아이디는 영어 소문자(a~z)로 시작하고, 소문자/숫자(0~9)/특수기호(_, -) 포함 5~20자 입니다.
					return false;
				}
				$.ajax({
					url: 'idCheck.do',
					type: 'get', // get방식 : 파라미터 값 그대로 전달
					data: 'mId=' + $('#mId').val(), // 보내는 data
					dataType: 'json', // 받는 data
					success: function(resultMap){
						if(resultMap.result == 0){ // DB에 일치하는 id가 없는 경우
							$('.id_result').text('사용 가능한 아이디입니다.');
							idPass = true;
						} else{
							$('.id_result').text('이미 사용 중인 아이디입니다.');
							idPass = false;
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			});
		}
		// 비밀번호 검증(pwCheck)
		var pwPass = false;
		function fn_pwCheck(){
			$('#mPw').keyup(function(){
				// 비밀번호 정규식
				var regPW = /^[0-9]{4}$/; 
				if(regPW.test($('#mPw').val())){ // 비밀번호 정규식 조건을 통과 했을 경우
					$('.pw_result').text('사용 가능한 비밀번호입니다.');
					pwPass = true;
				} else{
					$('.pw_result').text('비밀번호는 숫자(0~9) 4자리만 입력 가능합니다.');
					// 실제 사용할 PW 정규식
					// ^[A-Za-z0-9`~!@#$%^&*]{4,19}$
					// 비밀번호는 영어 대,소문자/숫자/특수기호 포함 5~20자리 입니다.
					pwPass = false;
				}
			});
		}
		// 비밀번호 확인 검증(pwCheck2)
		var pwPass2 = false;
		function fn_pwCheck2(){
			$('#mPw2').keyup(function(){
				if($('#mPw').val() == $('#mPw2').val()){ // 입력한 비밀번호와 비밀번호 확인 값이 일치할 경우
					$('.pw2_result').text('비밀번호가 일치합니다.');
					pwPass2 = true;
				} else{
					$('.pw2_result').text('입력하신 비밀번호가 일치하지 않습니다. 비밀번호를 확인하세요.');
					pwPass2 = false;
				}
			});
		}
		// 이메일 중복 체크(emailCheck)
		var emailPass = false;
		function fn_emailCheck(){
			$('#mEmail').keyup(function(){
				// 이메일 정규식
				var regEmail = /^[a-z0-9][a-z0-9_-]*@[a-zA-Z0-9]+([.][a-zA-Z]{2,}){1,2}$/;
				if(!regEmail.test($('#mEmail').val())){ // 이메일 정규식 조건을 통과하지 못 했을 경우
					$('.email_result').text('이메일 형식에 맞지 않습니다. 다시 입력하세요.');
					return false;
				}
				$.ajax({
					url: 'emailCheck.do',
					type: 'get',
					data: 'mEmail=' + $('#mEmail').val(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.result == 0){ // DB에 일치하는 email이 없는 경우
							$('.email_result').text('사용 가능한 이메일입니다. 인증코드를 받으세요.');
							emailPass = true;
						} else{
							$('.email_result').text('이미 사용 중인 이메일입니다. 이메일 주소를 확인하세요.');
							emailPass = false;
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			})
			
		}
		// 이메일 인증코드 받기(emailCode)
		// 이메일 인증코드 받기(root-context에서 이메일 bean 생성)
		function fn_emailCode(){
			$('#emailCode_btn').click(function(){
				if($('#mEmail').val() == ''){
					alert('이메일을 입력하세요.');
					$('#mEmail').focus();
					return false;
				}
				$.ajax({
					url: 'emailCode.do',
					type: 'get',
					data: 'mEmail=' + $('#mEmail').val(),
					dataType: 'json',
					success: function(resultMap){
						alert('인증코드가 발송되었습니다. 메일을 확인하세요.');
						fn_emailAuth(resultMap.authCode);
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			});
		}
		// 이메일 인증(emailAuth)
		var authPass = false;
		function fn_emailAuth(authCode){
			$('#emailAuth_btn').click(function(){
				if(authCode == $('#userKey').val()){ // 받은 인증코드와 입력된 값이 같을 경우
					$('.emailAuth_result').text('인증되었습니다.');
					authPass = true;
				} else{
					$('.emailAuth_result').text('인증에 실패했습니다. 다시 시도해주세요.');
					$('#mEmail').val() = '';
					authPass = false;
				}
			});
		}
		// 전화번호(phoneCheck)
		var phonePass = false;
		function fn_phoneCheck(){
			$('#mPhone').keyup(function(){
				// 전화번호 정규식
				var regPhone = /^010[0-9]{3,4}[0-9]{4}$/;
				if(regPhone.test($('#mPhone').val())){ // 전화번호 정규식 조건을 통과 했을 경우
					$('.phone_result').text('');
					phonePass = true;
				} else{
					$('.phone_result').text('010을 포함하여 -(하이픈) 없이 입력하세요.');
					phonePass = false;
				}
			});
		}
		// 회원가입(join)
		function fn_join() {
			$('#join_btn').click(function(){
				if($('#mName').val() == ''){
					alert('이름을 입력하세요.');
					return false;
				} else if(!idPass){
					alert('아이디를 확인하세요.');
					return false;
				} else if(!pwPass){
					alert('비밀번호를 확인하세요.');
					return false;
				} else if(!pwPass2){
					alert('비밀번호 확인을 검증하세요.');
					return false;
				} else if(!emailPass){
					alert('이메일을 확인하세요.');
				} else if(!authPass){
					alert('이메일 인증은 필수입니다.');
					return false;
				} else if(!phonePass){
					alert('전화번호를 올바르게 입력하세요.');
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
	
	<!-- 회원가입 화면  -->
	<div class="join_form">
		<form id="f" method="post">
			<span class="naming">이름</span><br>
			<input type="text" name="mName" id="mName" placeholder="ex) 홍길동 or hong"><br><br>
			
			<span class="naming">아이디</span><br>
			<input type="text" name="mId" id="mId" placeholder="ex) hong123"><br>
			<span class="id_result"></span><br><br>
			
			<span class="naming">비밀번호</span><br>
			<input type="password" name="mPw" id="mPw"><br>
			<span class="pw_result"></span><br><br>
			
			<span class="naming">비밀번호 확인</span><br>
			<input type="password" name="mPw2" id="mPw2"><br>
			<span class="pw2_result"></span><br><br>
	
			<span class="naming">이메일</span><br>
			<input type="text" name="mEmail" id="mEmail" placeholder="ex) hong123@example.com"><br>
			<span class="email_result"></span><br><br>
			<input type="button" value="인증코드 받기" id="emailCode_btn"><br>
			<input type="text" name="userKey" id="userKey">
			<input type="button" value="인증하기" id="emailAuth_btn"><br>
			<span class="emailAuth_result"></span><br><br>
			
			<span class="naming">전화번호</span><br>
			<input type="text" name="mPhone" id="mPhone" placeholder="-(하이픈)없이 입력"><br>
			<span class="phone_result"></span><br><br>
			
			<input type="button" value="가입하기" id="join_btn">
			<input type="button" value="돌아가기" onclick="location.href='index.do'">
		</form>
	</div>

</body>
</html>
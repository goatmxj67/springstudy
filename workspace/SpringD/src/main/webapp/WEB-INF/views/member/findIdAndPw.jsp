<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		// 페이지 로드
		$(document).ready(function(){
			fn_findId();
			fn_find_check();
		})
		// 아이디 찾기(findId)
		function fn_findId(){
			$('#findId_btn').click(function(){
				if($('#mName').val() == ''){
					alert('이름을 입력하세요');
					$('#mName').focus();
					return false;
				} else if($('#mEmail').val() == ''){
					alert('이메일을 입력하세요.');
					$('#mEmail').focus();
					return false;
				}
				
				var obj = { // 이름과 이메일 객체 생성
						mName : $('#mName').val(),
						mEmail : $('#mEmail').val()
				};
				
				$.ajax({
					url: 'findId.do',
					type: 'post',
					data: JSON.stringify(obj), // 보내는 data 문자열화
					contentType : 'application/json; charset=utf-8', // 보내는 데이터가 json일 때 필수 옵션
					dataType: 'json', // 받는 data
					success: function(resultMap){
						if(resultMap.status == 500){
							alert('입력하신 정보와 일치하는 계정이 없습니다.');
							return false;
						} else if(resultMap.status == 200){
							alert("회원님이 찾으시는 ID는" + resultMap.mId + "입니다.");
							return false;
						}
					},
					error: function(xhr, textStatus, errorThrown) {
						
					}
				});
			});
		}
		// 아이디/비밀번호 찾기 radio 체크에 따른 화면 전환
		function fn_find_check(){
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
	
	<!-- 아이디 찾기 화면 -->
	<div id="findId_form">
		<form id="f" method="post">
			<!-- 이름 -->
			<div class="form_group">
				<label for="mName">이름</label><br>
				<input type="text" name="mName" id="mName" placeholder="ex) 홍길동">
			</div>
			<!-- 이메일 -->
			<div class="form_group">
				<label for="mEmail">이메일</label><br>
				<input type="text" name="mEmail" id="mEmail" placeholder="ex) hong@naver.com">
			</div>
			<!-- 확인 버튼 -->
			<div class="form_group">
				<input type="button" id="findId_btn" value="확인">
			</div>
		</form>
	</div>
	<br><br>
	
	<!-- 비밀번호 찾기 화면 -->
	<div id="findPw_form" style="display: none;"> <!-- 처음에는 보이지 않고 비번찾기 시에 화면 나타내기 -->
		<form id="f2" method="post">
			<!-- 아이디 -->
			<div class="form_group">
				<label for="mId">아이디</label><br>
				<input type="text" name="mId" id="mId" placeholder="ex) hong">
			</div>
			<!-- 이메일 -->
			<div class="form_group">
				<label for="mEmail2">이메일</label><br>
				<input type="text" name="mEmail2" id="mEmail2" placeholder="ex) hong@naver.com">
			</div>
			<!-- 확인 버튼 -->
			<div class="form_group">
				<input type="button" id="findPw_btn" value="확인">
			</div>
		</form>
	</div>
	<br><br>
	
	<input type="button" value="로그인" onclick="location.href='index.do'">
	<input type="button" value="회원가입" onclick="location.href='joinPage.do'">


</body>
</html>

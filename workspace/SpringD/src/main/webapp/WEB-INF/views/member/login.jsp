<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		// 페이지 로드
		$(document).ready(function(){
			fn_login();
		});
		// 로그인(login)
		function fn_login(){
			$('#f').submit(function(e){
				if($('#mId').val() == '' || $('#mPw').val() == ''){
					alert('아이디와 비밀번호는 필수입니다.');
					e.preventDefault();
					$('#mId').focus();
					return false;
				}
			});
		}
	</script>
</head>
<body>

	<!-- 비로그인 화면 -->
	<c:if test="${loginUser == null}">
		<div class="login_form">
			<form id="f" action="login.do" method="post">
				<input type="text" name="mId" id="mId" placeholder="ID"><br>
				<input type="password" name="mPw" id="mPw" placeholder="Password"><br><br>
				<button>로그인</button>
			</form>
		</div>
		
		<!-- 회원가입, 아이디&비번 찾기 -->
		<div class="joinAndFind">
			<a href="joinPage.do">회원가입</a>
			<a href="findIdAndPwPage.do">아이디/비밀번호 찾기</a>
			<!-- <a href="findPwPage.do">비밀번호 찾기</a> -->
			<a href="index.do">돌아가기</a>
		</div>
	</c:if>
	
	<!-- 로그인 성공 화면 -->
	<c:if test="${loginUser != null}">
		<h3>${loginUser.MId} 님 환영합니다!</h3>
		<a href="boardPage.do">게시판</a>
		<a href="myPage.do">마이페이지</a>
		<a href="logout.do">로그아웃</a>
	</c:if>

</body>
</html>
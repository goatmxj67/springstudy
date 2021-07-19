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
		})
		// 아이디 찾기
		function fn_findId(){
			$('#findId_btn').click(function(){
				if($('#mEmail').val() == ''){
					alert('이메일을 입력하세요.');
					$('#mEmail').focus();
					return false;
				} else{
					$('#f').attr('action', 'findId.do');
					$('#f').submit();
				}
			});
		}
	</script>
</head>
<body>

	<h3>아이디 찾기</h3>
	
	<form id="f" method="post">
		가입 당시 이메일을 입력하세요.<br>
		<input type="text" name="mEmail" id="mEmail"><br><br>
		<input type="button" value="아이디 찾기" id="findId_btn">
		<input type="button" value="로그인" onclick="location.href='index.do'">
	</form>

</body>
</html>
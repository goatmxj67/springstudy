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
			fn_join();
		});
		function fn_join() {
			$('#f').submit(function(event){
				if ( fn_idCheck() ) {
					alert('가입된 아이디입니다.');
					$('#id').focus();
					event.preventDefault();
					return false;
				}
			});
		}
		function fn_idCheck() {
			
		}
	</script>
</head>
<body>

	<h1>회원가입</h1>
	
	<form id="f" action="join.do" method="post">
		
		아이디<br>
		<input type="text" name="id" id="id"><br><br>
		
		비밀번호<br>
		<input type="password" name="pw" id="pw"><br><br>
		
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2"><br><br>
		
		이름<br>
		<input type="text" name="name" id="name"><br><br>
		
		<button>가입하기</button>
		
	</form>

</body>
</html>
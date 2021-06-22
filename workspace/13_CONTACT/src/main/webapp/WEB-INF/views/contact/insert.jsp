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
			$('#list_btn').click(function(){
				location.href = 'selectContactList.do';
			})
			$('#f').submit(function(event){
				if ($('#name').val() == '' || 
					$('#tel').val() == '' || 
					$('#addr').val() == '' ||
					$('#email').val() == '') {
					alert('필수 정보를 입력하세요.');
					event.preventDefault();
					return false;
				}
				$('#f').attr('action', 'insertContact.do');
				$('#f').submit();
			})
		})
	</script>
</head>
<body>

	<h3>연락처 등록</h3>
	
	<form id="f" method="post">
		이름*<br>
		<input type="text" name="name" id="name"><br><br>
		전화*<br>
		<input type="text" name="tel" id="tel"><br><br>
		주소*<br>
		<input type="text" name="addr" id="addr"><br><br>
		이메일*<br>
		<input type="text" name="email" id="email"><br><br>
		비고<br>
		<input type="text" name="note" id="note"><br><br>
		<button>연락처 저장하기</button>
		<input type="button" value="전체연락처" id="list_btn">
	</form>

</body>
</html>
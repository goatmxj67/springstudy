<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		// 페이지 로드
		$(document).ready(function(){
			fn1();
			fn2();
			fn3();
		});
		// 함수
		function fn1() {
			$('#btn1').click(function(){
				$.ajax({
					url: 'v01',    // @RequestMapping(value="v01")
					type: 'get',   // @RequestMapping(method=RequestMethod.GET)
					data: 'name=' + $('#name').val() + "&age=" + $('#age').val(),  // @RequestParam("name"), @RequestParam("age")
					dataType: 'text',  // @RequestMapping(produces="text/plain; charset=utf-8")
					success: function(responseData) {  // responseData에 return으로 반환한 String 데이터가 저장된다.
						console.log(responseData);
					},
					error: function(xhr, text, error) {
						console.log(text + ", " + error);
					}
				});
			});
		}  // end fn1()
		function fn2() {
			$('#btn2').click(function(){
				$.ajax({
					url: 'v02',
					type: 'get',
					data: $('#f').serialize(),  // form의 모든 요소를 한 번에 보냄 (파라미터로 보냄)
					dataType: 'json',
					success: function(responseData){
						console.log(responseData);
					},
					error: function(xhr, text, error) {
						console.log(text + ", " + error);
					}
				})
			})
		}  // end fn2()
		function fn3() {
			$('#btn3').click(function(){
				// 서버로 보낼 json 데이터 (문자열로 보낸다.)
				var obj = JSON.stringify({
						"name": $('#name').val(),
						"age": $('#age').val()
				});
				$.ajax({
					url: 'v03',
					type: 'post',  // json 데이터를 보낼 때 반드시 본문에 포함해서 보낸다. (post로 보낸다.)
					data: obj,  // 서버로 보내는 json 데이터, 파라미터가 아니다.(@RequestParam 대신 @RequestBody 애너테이션을 사용한다.)
					contentType: 'application/json',  // 서버로 보내는 데이터의 타입
					dataType: 'json',  // 서버에서 받는 데이터의 타입
					success: function(responseData) {
						console.log(responseData);
					},
					error: function(xhr, text, error) {
						console.log(text + "," + error);
					}
				})
			})  
		}  // end fn2()
	</script>
</head>
<body>
	
	<form id="f">
		<input type="text" name="name" id="name" placeholder="이름"><br>
		<input type="text" name="age" id="age" placeholder="나이"><br>
		<input type="button" value="전송1" id="btn1">
		<input type="button" value="전송2" id="btn2">
		<input type="button" value="전송3" id="btn3">
	</form>
	
</body>
</html>
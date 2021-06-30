<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			fn_search();
			fn_init();
			fn_search_all();
			fn_auto_complete();
		});
		function fn_search(){
			$('#search_btn').click(function(){
				if ($('#column').val() == '') {
					alert('검색 카테고리를 선택하세요.');
					$('#column').focus();
					return false;
				}
				$('#f').attr('action', 'search.do');
				$('#f').submit();
			});
		}
		function fn_init(){
			$('#column').val('');
			$('#query').val('');
		}
		function fn_search_all(){
			$('#search_all_btn').click(function(){
				location.href = 'searchAll.do';
			});
		}
		function fn_auto_complete(){
			$('#query').keyup(function(){
				$('#auto_complete_list').empty();  // 내부 value 태그 모두 지워줌
				var obj = {
					column: $('#column').val(),
					query: $('#query').val()
				};
				$.ajax({
					url: 'autoComplete.do',
					type: 'post',
					contentType: 'application/json',
					data: JSON.stringify(obj),
					dataType: 'text',
					success: function(resultMap){
						console.log(resultMap);
						var result = JSON.parse(resultMap);
						if (result.status == 200) {
							$.each(result.list, function(i, employee){
								$('<option>')
								.val(employee[$('#column').find('option[value="' + obj.column + '"]').data('name')])
								.appendTo('#auto_complete_list');
							});
						}
					},
					error: function() {
						alert('실패');
					}
				});
			});
		}
	</script>
</head>
<body>

	<!-- 검색화면 -->
	<form id="f" method="get">
		<select name="column" id="column">
			<option value="">:::::선택:::::</option>
			<option value="EMPLOYEE_ID" data-name="employeeId">EMPLOYEE_ID</option>
			<option value="FIRST_NAME" data-name="firstName">FIRST_NAME</option>
			<option value="LAST_NAME" data-name="lastName">LAST_NAME</option>
			<option value="SALARY" data-name="salary">SALARY</option>
		</select>
		<input list="auto_complete_list" type="text" name="query" id="query">
		<datalist id="auto_complete_list">
		</datalist>
		<input type="number" name="bottom" placeholder="최소연봉">
		<input type="number" name="top" placeholder="최대연봉">
		<input type="button" value="검색" id="search_btn">
		<input type="button" value="초기화" id="init_btn">
		<input type="button" value="전체조회" id="search_all_btn">
	</form>

</body>
</html>
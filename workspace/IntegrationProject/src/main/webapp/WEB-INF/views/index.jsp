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
			fn_init();
			fn_selectAll();
			fn_selectQuery();
		});
		function fn_init() {
			$('#init_btn').click(function(){
				$('#column').val('');
				$('#query').val('');
				fn_selectAll();
			});
		}
		function fn_selectAll() {
			$.ajax({
				url: 'selectAll.do',
				type: 'get',
				dataType: 'json',
				success: function(resultMap) {
					alert(resultMap.message);
					fn_listTable(resultMap.status, resultMap.list);
				}
			});
		}
		function fn_listTable(status, list) {
			$('#list').empty();
			if (status == 200) {
				$.each(list, function(i, board) {
					$('<tr>')
					.append( $('<td>').text(board.no) )
					.append( $('<td>').text(board.title) )
					.append( $('<td>').text(board.content) )
					.append( $('<td>').text(board.writer) )
					.append( $('<td>').text(board.postdate) )
					.appendTo('#list');
				});
			} else if (status == 500) {
				$('<tr>')
				.append( $('<td colspan="5">').text('없음') )
				.appendTo('#list');
			}
		}
		function fn_selectQuery() {
			$('#search_btn').click(function(){
				if ($('#column').val() == '') {
					alert('검색 카테고리를 선택하세요.');
					$('#column').focus();
					return false;
				}
				$.ajax({
					url: 'selectQuery.do',
					type: 'get',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(resultMap) {
						alert(resultMap.message);
						fn_listTable(resultMap.status, resultMap.list);
					}
				});
			});
		}
	</script>
</head>
<body>

	<form method="get" id="f">
		<select id="column" name="column">
			<option value="">:::::선택:::::</option>
			<option value="TITLE">제목</option>
			<option value="WRITER">작성자</option>
			<option value="CONTENT">내용</option>
			<option value="BOTH">제목+내용</option>
		</select>
		<input type="text" id="query" name="query">
		<input type="button" value="검색" id="search_btn">
		<input type="button" value="초기화" id="init_btn">
	</form>
	
	<br><br>
	
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>내용</td>
				<td>작성자</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody id="list">
			
		</tbody>
	</table>

</body>
</html>
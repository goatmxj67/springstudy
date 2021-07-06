<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(function(){
			fn_searchAll();
			fn_search();
			fn_init();
		});
		function fn_showList(list, status) {
			$('#list').empty();
			if (status == 200) {
				$.each(list, function(idx, board) {
					$('<tr>')
					.append( $('<td>').html(board.title) )
					.append( $('<td>').html(board.content.substr(0, 15)) )
					.append( $('<td>').html(getDateFormat(board.regdate)) )
					.appendTo('#list');
				});
			} else if (status == 500) {
				$('<tr>')
				.append( $('<td colspan="3">').html('검색 결과 없음') )
				.appendTo('#list');
			}
		}
		function getDateFormat(date) {
			var d = new Date(date);
			var year = d.getFullYear();
			var month = '' + (d.getMonth() + 1);
			var day = '' + d.getDate();
			if (month.length < 2) {
			    month = '0' + month;
			}
			if (day.length < 2) {
			     day = '0' + day;
			}
			return [year, month, day].join('-');
		}
		function fn_searchAll() {
			$.ajax({
				url: 'searchAll.do',
				type: 'get',
				dataType: 'json',
				success: function(result) {
					console.log(JSON.stringify(result));
					alert(result.message);
					fn_showList(result.list, result.status);						
				}
			});
		}
		function fn_search() {
			$('#searchBtn').click(function() {
				$('#searchBoardList').empty();
				$.ajax({
					url: 'search.do',
					type: 'get',					
					data: 'column=' + $('#column').val() + '&searchText=' + $('#searchText').val(),
					dataType: 'json',
					success: function(result) {
						console.log(JSON.stringify(result));
						alert(result.message);
						fn_showList(result.list, result.status);
					}
				});
			});
		}
		function fn_init() {
			$('#initBtn').click(function(){
				fn_searchAll();
			});
		}
	</script>
</head>
<body>

	<form method="get">
		
		<select id="column" name="column">
			<option value="TITLE">제목</option>
			<option value="CONTENT">내용</option>
		</select>
		<input type="text" id="searchText" name="searchText">
		<input type="button" id="searchBtn" value="검색">
		<input type="button" id="initBtn" value="초기화">
		
		<br><hr><br>
		
		<table border="1">
			<thead>
				<tr>
					<th>제목</th>
					<th>내용</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody id="list">
			
			</tbody>
		</table>
	</form>

</body>
</html>
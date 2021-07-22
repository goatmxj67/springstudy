<%@page import="java.text.SimpleDateFormat"%>
<%@page import="javax.swing.text.SimpleAttributeSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" referrerpolicy="no-referrer" />
	<link rel="stylesheet" href="resources/css/loginWindow.css"> 
	<title>게시글</title>
	<style>
		table{
			text-align: center;
		}
		
		#notice{
			background-color: orange;	
		}
	</style>
	
	<script>
		$(function(){
			fn_showLogin();	// 로그인 버튼 클릭시 로그인창이 펴지는 함수
			getDate();		// 시간 보여주는 함수
			fn_closeLogin();	// 로그인창에서 x 클릭시 로그인창 닫힘
			fn_toggle_mode(); 	// 관리자 로그인 모드 / 회원 로그인 모드로 변경하는 버튼
			fn_insertBoard();	// 새 글 작성 버튼 클릭시 로그인창 켜지거나 페이지 이동하는 함수
			
			fn_init();	// 전체 목록 불러오기 버튼
			click_search();
			
			
			if(${query == null}){	
				// 만약 model을 통해 query가 채워지지 않은 상태라면,
				// search 결과가 아니라 전체 목록을 가져온다.
				fn_showList();	// 관리자의 글 제외하고 글 목록 가져오기			
			} else{
				// 만약 검색 버튼을 통해 검색한 후, paging된 숫자를 눌렀다면,
				// 전체 목록이 아니라 search결과만을 띄워준다.
				fn_search();	// 검색버튼 클릭시 리스트 불러오는 함수
			}
		})	// onload 함수의 끝
		
		// 관리자의 글을 공지사항으로 가져오기
		function fn_get_notice(){
			$.ajax({
				url: 'selectNotice.do',
				type: 'get',
				async: false,
				dataType: 'json',
				success: function(resultMap){
					console.log('공지');
					fn_makeNotice(resultMap.list);
				},	// success
				error: function(){
					alert('공지사항 오류');
				}
			});	// ajax
		}
		
		// 멤버 로그인, 관리자 로그인을 변경해주는 함수
		function fn_toggle_mode(){
			$('#mem_to_admin').click(function(){
				$('#mem_mode').toggleClass('disabled');
				$('#admin_mode').toggleClass('disabled');
			});
			
			$('#admin_to_mem ').click(function(){
				$('#mem_mode').toggleClass('disabled');
				$('#admin_mode').toggleClass('disabled');
			});	
		}
		
		function click_search(){
			$('#search_btn').click(function(){
				fn_search();
			});
		}
		
		// 검색 목록 불러오는 ajax
		 function fn_search(){
				$('#list').empty();
				fn_get_notice();
				$.ajax({
					url: 'searchBoard.do',
					type: 'get',
					async: false,
					data: 'column=' + $('#column').val() + '&query=' + $('#query').val() + '&page=${page}',
					dataType: 'json',
					success: function(resultMap){
						fn_makeTable(resultMap.list);
						$('#paging').empty();
						$('#paging').html(resultMap.paging);
					},	// success
					error: function(){
						alert('search_btn 오류');
					}
				});	// ajax	
		}	// fn_search 
		
		function fn_init(){
			$('#init_btn').click(function(){
				$('#list').empty();
				fn_get_notice();
				fn_showList();
			});	// onclick
		}	// fn_init
		
		function fn_insertBoard(){
			$('#insert_board_btn').click(function(){
				if(${mode == "member"}){
					if( ${loginUser == null} ){	// 로그인된 상태가 아니라면
						alert('로그인이 필요합니다.');
						$('.form').toggleClass('hide');
					} else{	// 로그인된 상태라면
						location.href= "insertBoardPage.do";
					}
				} // if member
				else if(${mode == "admin"}){
					if( ${loginAdmin == null} ){	// 로그인된 상태가 아니라면
						alert('로그인이 필요합니다.');
						$('.form').toggleClass('hide');
					} else{	// 로그인된 상태라면
						location.href= "insertBoardPage.do";
					}			
				}	// else if admin
				else{
					alert('로그인이 필요합니다.');
					$('.form').toggleClass('hide');
				}	// else
					
				
			});	// onclick
		}	// fn_insertBoard
		
		function fn_closeLogin(){	// 로그인창에서 x 클릭시 로그인창 닫힘
			$('#closeLogin').click(function(){
				$('.form').toggleClass('hide');
			})	// onclick#
		}	// fn_closeLogin
		
		function fn_showLogin(){
			$('.showLogin').on('click',function(){
				$('.form').toggleClass('hide');
			});	// onclick
		}	// fn_login_btn
		
	    function getDate() { 
	        date = setInterval(function () { 
	            var dateString = ""; 
	            var newDate = new Date(); 
	            dateString += ("0" + newDate.getHours()).slice(-2) + ":"; 
	            dateString += ("0" + newDate.getMinutes()).slice(-2) ; 
	            //document.write(dateString); 문서에 바로 그릴 수 있다. 
	            $("#date").text(dateString); 
	        }, 1000);	// 1초 단위로  
	    } 
		
		function fn_showList(){
			$('#list').empty();
			fn_get_notice();	// 관리자의 글 공지사항으로 가져오기
			$.ajax({
				url: 'showList.do',
				type: 'get',
				async: false,
				data: 'page=${page}',
				dataType: 'json',
				success: function(resultMap){
					fn_makeTable(resultMap.list);
					$('#paging').empty();
					$('#paging').html(resultMap.paging);
				},	// end of success
				error: function(){
					alert('목록 불러오기 오류');
				}
			})
		}	// fn_showList
		
		// list 출력함수
		function fn_makeTable(list){
			$.each(list, function(i, board){
				if(board.bfilename1 == 'null' || board.bfilename2 == 'null' || board.bfilename3 == 'null'){
					$('<tr>')
					.append( $('<td>').text(board.bidx) )
					.append( $('<td>').text(board.mid) )
					.append( $('<td>').html('<a href="selectBoard.do?bIdx=' + board.bidx + '">' + board.btitle + '</a>') )
					.append( $('<td>').text(board.bpostDate) )
					.append( $('<td>').text(board.bhit) )
					.append( $('<td>').text('') )
					.appendTo('#list');
				}
				else{
					$('<tr>')
					.append( $('<td>').text(board.bidx) )
					.append( $('<td>').text(board.mid) )
					.append( $('<td>').html('<a href="selectBoard.do?bIdx=' + board.bidx + '">' + board.btitle + '</a>') )
					.append( $('<td>').text(board.bpostDate) )
					.append( $('<td>').text(board.bhit) )
					.append( $('<td>').html('<i class="far fa-image"></i>') )
					.appendTo( $('#list') ); 
				} 
			});	// each 
			
		}
			
		
		// 공지사항 출력 함수
		function fn_makeNotice(list){
			$.each(list, function(i, board){
				if(board.bfilename1 == 'null' || board.bfilename2 == 'null' || board.bfilename3 == 'null'){
					$('<tr id="notice">')
					.append( $('<td>').text(board.bidx) )
					.append( $('<td>').text(board.mid) )
					.append( $('<td>').html('<a href="selectBoard.do?bIdx=' + board.bidx + '">' + board.btitle + '</a>') )
					.append( $('<td>').text(board.bpostDate) )
					.append( $('<td>').text(board.bhit) )
					.append( $('<td>').text('') )
					.appendTo('#list');
				}
				else{
					$('<tr id="notice">')
					.append( $('<td>').text(board.bidx) )
					.append( $('<td>').text(board.mid) )
					.append( $('<td>').html('<a href="selectBoard.do?bIdx=' + board.bidx + '">' + board.btitle + '</a>') )
					.append( $('<td>').text(board.bpostDate) )
					.append( $('<td>').text(board.bhit) )
					.append( $('<td>').html('<i class="far fa-image"></i>') )
					.appendTo( $('#list') ); 
				} 
			});	// each 
			
		}	// fn_makeNotice
		
	</script>
	
<body>
 
	 
	<h1>게시판</h1>
		
		<div class="btn_box">
			<span>Seoul</span>
			<span id="date"></span>
			<c:if test="${loginUser eq null && loginAdmin eq null}">
	 			<input type="button" class="showLogin" value="로그인">
	 		</c:if>
	 		<c:if test="${loginUser ne null || loginAdmin ne null}">
	 			<input type="button" value="로그아웃" onclick="location.href='logout.do'">
			</c:if>
		</div>
		<div id="mem_mode" class="myMenu">
			<form action="login.do" method="post">
  	   	 	<div class="form hide">
  	   	 		<input type="hidden" name="page" value="board/viewBoardList">
  	   	 		<h2 style="text-align:center">회원 로그인</h2>
				<a id="closeLogin"><i class="fas fa-times fa-3x"></i></a>
   				 <div class="form2">
     				<div class="form3">
     					<label for="id">아이디</label><input type="text" name="mId" id="mId">
      					<div class="clear"></div>
      					<label for="password">비밀번호</label><input type="password" name="mPw" id="mPw">
     				</div>	<!-- form3 -->
     				<input type="submit" id="login_btn" value="로그인">
     				<div class="clear"></div>
     				<div class="form4">
      					<div class="clear"></div>
     						<label><input type="button" value="회원가입"></label>
     						<label><input type="button" value="아이디/비밀번호 찾기"></label>
     						<label><input type="button" id="mem_to_admin" value="관리자로 로그인하기"></label>
					</div>	<!-- form4 -->
				</div>	<!-- form2 -->
			</div>	<!-- form -->
			</form>
		</div>	<!-- myMenu -->
		
		<div id="admin_mode" class="myMenu disabled">
			<form action="loginAdmin.do" method="post">
  	   	 	<div class="form hide">
  	   	 		<input type="hidden" name="page" value="board/viewBoardList">
  	   	 		<h2 style="text-align:center">관리자 로그인</h2>
				<a id="closeLogin"><i class="fas fa-times fa-3x"></i></a>
   				 <div class="form2">
     				<div class="form3">
     					<label for="id">아이디</label><input type="text" name="mId" id="mId">
      					<div class="clear"></div>
      					<label for="password">비밀번호</label><input type="password" name="mPw" id="mPw">
     				</div>	<!-- form3 -->
     				<input type="submit" id="login_btn" value="로그인">
     				<div class="clear"></div>
     				<div class="form4">
      					<div class="clear"></div>
     						<label><input type="button" id="admin_to_mem" value="회원으로 로그인하기"></label>
					</div>	<!-- form4 -->
				</div>	<!-- form2 -->
			</div>	<!-- form -->
			</form>
		</div>	<!-- myMenu -->
		
	<!-- 검색한 결과를 띄워줄 때는, 그 전에 사용했던 column이 선택되어있도록 해준다. -->
	<select id="column">
		<c:if test="${column eq 'BTITLE'}">
			<option value="BTITLE" selected>제목</option>
		</c:if>
		<c:if test="${column ne 'BTITLE'}">
			<option value="BTITLE">제목</option>
		</c:if>
		<c:if test="${column eq 'MID'}">
			<option value="MID" selected>작성자</option>
		</c:if>
		<c:if test="${column ne 'MID'}">
			<option value="MID">작성자</option>
		</c:if>
		<c:if test="${column eq 'BCONTENT'}">
			<option value="BCONTENT" selected>내용</option>
		</c:if>
		<c:if test="${column ne 'BCONTENT'}">
			<option value="BCONTENT">내용</option>
		</c:if>

	</select>
	
	<!-- 검색했을 때 입력한 값 그대로 query를 유지해준다. -->
	<input type="text" id="query" value="${query}">
	
	<input type="button" id="search_btn" value="검색">
	<input type="button" id="init_btn" value="전체 목록 보기">
	
	<table border="1">
		<thead>
			<tr>
				<td> 글번호 </td>
				<td> 작성자 </td>
				<td> 제목 </td>
				<td> 작성일 </td>
				<td> 조회수 </td>
				<td> 첨부파일</td>
			</tr>
		</thead>
	
		<tbody id="list">
			
		</tbody>
		
		<tfoot id="paging">
		
		</tfoot>
	</table>
	<input type="button" id="insert_board_btn" value="새 글 작성">
</body>
</html>
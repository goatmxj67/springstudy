<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디 찾기</title>
</head>
<body>

	<h3>아이디 찾기 결과</h3>
	
	<c:if test="${findUser == null}">
		일치하는 회원 정보가 없습니다. 이메일을 확인하세요.<br><br>
		<a href="findIdPage.do">아이디 찾기</a>
		<a href="join.do">회원가입</a>
	</c:if>
	
	<c:if test="${findUser != null}">
		회원가입 시 사용한 아이디는 ${findUser.MId}입니다<br>
		(가입일: ${findUser.MRegdate})<br><br>
		<input type="button" value="로그인" onclick="location.href='index.do'">
		<input type="button" value="비밀번호 찾기" onclick="location.href='findPwPage.do'">
	</c:if>

</body>
</html>

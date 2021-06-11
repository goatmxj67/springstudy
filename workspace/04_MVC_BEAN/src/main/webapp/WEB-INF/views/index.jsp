<%@page import="com.koreait.mvc02.dto.Member"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%
		// 생성된 bean들은 application에 저장된다.
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(application);
		
		Member m1 = ctx.getBean("member1", Member.class);
		Member m2 = ctx.getBean("member2", Member.class);
		
		// EL 사용을 위해서
		pageContext.setAttribute("m1", m1);
		pageContext.setAttribute("m2", m2);
	%>

	<h1>사용자1</h1>
	아이디: ${m1.id}<br>
	전화번호 ${m1.contact.phone}<br>
	주소 ${m1.contact.address}<br>
	
	<!-- root-context.xml -->
	<h1>사용자2</h1>
	아이디: ${m2.id}<br>
	전화번호 ${m2.contact.phone}<br>
	주소 ${m2.contact.address}<br>
	
	<!-- BeanConfiguration.java -->
	<h1>사용자3</h1>
	아이디: ${m3.id}<br>
	전화번호 ${m3.contact.phone}<br>
	주소 ${m3.contact.address}<br>
	
</body>
</html>
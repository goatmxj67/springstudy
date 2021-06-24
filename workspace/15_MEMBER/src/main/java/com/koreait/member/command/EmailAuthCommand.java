package com.koreait.member.command;

import java.util.HashMap;
import java.util.Map;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;

public class EmailAuthCommand {

	@Autowired
	private JavaMailSender mailSender;
	
	public Map<String, String> execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String email = request.getParameter("email");  // 인증번호를 받는 사람 이메일
		String authCode = "";
		
		// MimeMessage 클래스
		// 이메일을 작성하는 클래스
		// forspringlec@gmail.com
		MimeMessage message = mailSender.createMimeMessage();
		try {
			message.setHeader("content-Type", "text/plain; charset=utf-8");
			message.setFrom(new InternetAddress("yulki67@gmail.com", "관리자"));  // 보내는 사람
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));  // 받는 사람
			message.setSubject("인증 요청 메일입니다.");
			
			char[] charcters = {
					'A', 'B', 'C', 'D', 'E',
					'F', 'G', 'H', 'I', 'J',
					'K', 'L', 'M', 'N', 'O',
					'P', 'Q', 'R', 'S', 'T',
					'U', 'V', 'W', 'X', 'Y',
					'Z', '0', '1', '2', '3',
					'4', '5', '6', '7', '8',
					'9', '!', '$', '?', '&',
			};
			for (int i = 0; i < 6; i++) {
				authCode += charcters[(int)(Math.random() * charcters.length)];
			}
			message.setText("인증번호는 " + authCode + "입니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 이메일 보내기
		mailSender.send(message);
		
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("authCode", authCode);
		return resultMap;
		
	}

}

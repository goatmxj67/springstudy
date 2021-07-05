package com.koreait.mine.command.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.koreait.mine.dto.Member;
import com.koreait.mine.util.SecurityUtils;

public class PresentPwCheckCommand {

	public Map<String, Boolean> execute(Model model) {

		Map<String, Object> map = model.asMap();
		Member member = (Member)map.get("member");
		HttpSession session = (HttpSession)map.get("session");
		
		String pw1 = ((Member)session.getAttribute("loginUser")).getPw();  // "MTExMQ=="
		String pw2 = SecurityUtils.encodeBase64(member.getPw());  // 암호화: "1111" -> "MTExMQ=="
		
		Map<String, Boolean> resultMap = new HashMap<>();
		resultMap.put("isCorrect", pw1.equals(pw2));
		return resultMap;
		
	}

}

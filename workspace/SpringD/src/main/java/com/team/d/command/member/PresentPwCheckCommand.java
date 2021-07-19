package com.team.d.command.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.team.d.dto.MemberDTO;
import com.team.d.util.SecurityUtils;

public class PresentPwCheckCommand {

public Map<String, Boolean> execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		MemberDTO memberDTO = (MemberDTO)map.get("memberDTO");
		HttpSession session = (HttpSession)map.get("session");
		
		// session에 저장된 비밀번호(pw1)와 현재 입력한 비밀번호 확인하기(pw2)
		String pw = ((MemberDTO)session.getAttribute("loginUser")).getMPw(); // session에 저장된 암호화 된 비밀번호
		// String pw2 = SecurityUtils.encodeBase64(memberDTO.getMPw()); // 현재 입력한 비밀번호 암호화
		
		// 마이페이지 화면에서 입력한 비밀번호 확인
		String mPw = SecurityUtils.encodeBase64(memberDTO.getMPw()); // 입력한 비밀번호 암호화
		
		
		Map<String, Boolean> resultMap = new HashMap<>();
		resultMap.put("isCorrect", pw.equals(mPw)); // pw과 mPw 비교
		return resultMap;

	}

}
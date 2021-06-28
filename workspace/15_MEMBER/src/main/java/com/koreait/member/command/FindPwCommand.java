package com.koreait.member.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.dao.MemberDAO;
import com.koreait.member.dto.Member;
import com.koreait.member.util.SecurityUtils;

public class FindPwCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		
		Member member = new Member();
		member.setPw(SecurityUtils.encodeBase64(pw));
		member.setEmail(email);
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		memberDAO.changePw(member);
		
	}

}

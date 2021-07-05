package com.koreait.mine.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.mine.dao.MemberDAO;
import com.koreait.mine.dto.Member;
import com.koreait.mine.util.SecurityUtils;

public class JoinCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
	
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		Member member = new Member();
		member.setId(id);
		member.setPw(SecurityUtils.encodeBase64(pw));  // pw의 암호화
		member.setName(SecurityUtils.xss(name));  // name의 xss처리
		member.setPhone(phone);
		member.setEmail(email);
		member.setAddress(address);
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		memberDAO.join(member);
		
	}

}
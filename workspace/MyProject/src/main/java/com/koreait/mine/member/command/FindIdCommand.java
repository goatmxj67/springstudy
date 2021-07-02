package com.koreait.mine.member.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.mine.dao.MemberDAO;
import com.koreait.mine.dto.Member;

public class FindIdCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		String email = request.getParameter("email");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		Member findUser = memberDAO.findId(email);
		
		if (findUser != null) {
			model.addAttribute("findUser", findUser);  // 검색 결과를 표시할 JSP로 전달하기 위해서		
		}
		
	}

}

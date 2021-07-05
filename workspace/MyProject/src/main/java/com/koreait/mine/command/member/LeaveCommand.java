package com.koreait.mine.command.member;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.mine.dao.MemberDAO;
import com.koreait.mine.dto.Member;

public class LeaveCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpSession session = (HttpSession)map.get("session");
		
		long no = ((Member)session.getAttribute("loginUser")).getNo();
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		memberDAO.changeState(no);
		int count = memberDAO.leave(no);

		if (count > 0) {
			session.invalidate();
		}
		
	}

}

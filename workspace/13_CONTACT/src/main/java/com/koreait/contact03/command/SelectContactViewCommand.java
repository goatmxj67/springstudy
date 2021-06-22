package com.koreait.contact03.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;

public class SelectContactViewCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		long no = Long.parseLong(request.getParameter("no"));
		
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		model.addAttribute("contact", contactDAO.selectContactByNo(no));

	}

}
package com.koreait.contact03.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;

public class SelectContactListCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		model.addAttribute("list", contactDAO.selectContactList());

	}

}
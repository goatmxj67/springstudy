package com.koreait.contact01.command;

import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;

public class ContactListCommand implements ContactCommand {

	@Override
	public void execute(Model model) {
		
		// Model : JSP에게 값을 전달할 때 사용한다.
		
		model.addAttribute("list", ContactDAO.getInstance().selectContactList());

	}

}

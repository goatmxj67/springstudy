package com.koreait.contact01.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;

public class SelectContactListCommand implements ContactCommand {

	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		model.addAttribute("list", contactDAO.selectContactList());
	}

}
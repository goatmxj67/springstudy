package com.koreait.contact01.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;

public class SelectContactViewCommand implements ContactCommand {

	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		long no = Long.parseLong(request.getParameter("no"));
		model.addAttribute("contact", contactDAO.selectContactByNo(no));
	}

}
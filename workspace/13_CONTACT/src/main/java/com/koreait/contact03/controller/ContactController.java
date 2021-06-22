package com.koreait.contact03.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.contact03.command.DeleteContactCommand;
import com.koreait.contact03.command.InsertContactCommand;
import com.koreait.contact03.command.SelectContactListCommand;
import com.koreait.contact03.command.SelectContactViewCommand;
import com.koreait.contact03.command.UpdateContactCommand;

@Controller
public class ContactController {

	// field
	private SqlSession sqlSession;
	private SelectContactListCommand selectContactListCommand;
	private SelectContactViewCommand selectContactViewCommand;
	private InsertContactCommand insertContactCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	
	// constructor
	@Autowired
	public ContactController(SqlSession sqlSession,
							 SelectContactListCommand selectContactListCommand,
							 SelectContactViewCommand selectContactViewCommand, 
							 InsertContactCommand insertContactCommand,
							 UpdateContactCommand updateContactCommand, 
							 DeleteContactCommand deleteContactCommand) {
		this.sqlSession = sqlSession;
		this.selectContactListCommand = selectContactListCommand;
		this.selectContactViewCommand = selectContactViewCommand;
		this.insertContactCommand = insertContactCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}
	
	// method
	@GetMapping(value= {"/", "selectContactList.do"})
	public String selectContactList(Model model) {
		selectContactListCommand.execute(sqlSession, model);
		return "contact/list";
	}
	
	@GetMapping(value="selectContactView.do")
	public String selectContactView(HttpServletRequest request,
									Model model) {
		model.addAttribute("request", request);
		selectContactViewCommand.execute(sqlSession, model);
		return "contact/view";
	}
	
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		return "contact/insert";
	}
	
	@PostMapping(value="insertContact.do")
	public void insertContact(HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		insertContactCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value="updateContact.do")
	public void updateContact(HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		updateContactCommand.execute(sqlSession, model);
	}
	
	@GetMapping(value="deleteContact.do")
	public void deleteContact(HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		deleteContactCommand.execute(sqlSession, model);
	}
	
}
package com.koreait.contact02.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.koreait.contact02.command.DeleteContactCommand;
import com.koreait.contact02.command.InsertContactCommand;
import com.koreait.contact02.command.SelectContactListCommand;
import com.koreait.contact02.command.SelectContactViewCommand;
import com.koreait.contact02.command.UpdateContactCommand;

@Controller
public class ContactController {

	// field
	private SelectContactListCommand selectContactListCommand;
	private SelectContactViewCommand selectContactViewCommand;
	private InsertContactCommand insertContactCommand;
	private UpdateContactCommand updateContactCommand;
	private DeleteContactCommand deleteContactCommand;
	
	// constructor
	@Autowired
	public ContactController(SelectContactListCommand selectContactListCommand,
							 SelectContactViewCommand selectContactViewCommand, 
							 InsertContactCommand insertContactCommand,
							 UpdateContactCommand updateContactCommand, 
							 DeleteContactCommand deleteContactCommand) {
		this.selectContactListCommand = selectContactListCommand;
		this.selectContactViewCommand = selectContactViewCommand;
		this.insertContactCommand = insertContactCommand;
		this.updateContactCommand = updateContactCommand;
		this.deleteContactCommand = deleteContactCommand;
	}
	
	// method
	@GetMapping(value= {"/", "selectContactList.do"})
	public String selectContactList(Model model) {
		selectContactListCommand.execute(model);
		return "contact/list";
	}
	
	@GetMapping(value="selectContactView.do")
	public String selectContactView(HttpServletRequest request,
									Model model) {
		model.addAttribute("request", request);
		selectContactViewCommand.execute(model);
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
		insertContactCommand.execute(model);
	}
	
	@PostMapping(value="updateContact.do")
	public void updateContact(HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		updateContactCommand.execute(model);
	}
	
	@GetMapping(value="deleteContact.do")
	public void deleteContact(HttpServletRequest request,
							  HttpServletResponse response,
							  Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		deleteContactCommand.execute(model);
	}
	
}
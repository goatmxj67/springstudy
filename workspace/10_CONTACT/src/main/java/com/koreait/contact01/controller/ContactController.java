package com.koreait.contact01.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.contact01.command.ContactListCommand;
import com.koreait.contact01.command.ContactViewCommand;
import com.koreait.contact01.command.DeleteContactCommand;
import com.koreait.contact01.command.InsertContactCommand;
import com.koreait.contact01.command.UpdateContactCommand;
import com.koreait.contact01.config.BeanConfiguration;
import com.koreait.contact01.dto.Contact;

@Controller
public class ContactController {

	// field
	private static final Logger logger = LoggerFactory.getLogger(ContactController.class);
	
	// BeanConfiguration.java 이용한 bean 생성
	private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	
			
	// method
	@GetMapping(value="/")  // @RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		logger.info("index() 호출");
		return "index";
	}
	
	@GetMapping(value="selectContactList.do")
	public String selectContactList(Model model) {
		logger.info("selectContactList() 호출");
		ContactListCommand command = ctx.getBean("contactListCommand", ContactListCommand.class);
		command.execute(model);
		return "contact/list";  // contact/list.jsp로 이동
	}
	
	@GetMapping(value="insertContactPage.do")
	public String insertContactPage() {
		logger.info("insertContactPage() 호출");
		return "contact/insert";  // contact/insert.jsp로 이동
	}
	
	@PostMapping(value="insertContact.do")
	public String insertContact(HttpServletRequest request,  // <form> 태그 요소가 파라미터로 전달된다.
							  Model model) {
		logger.info("insertContact() 호출");
		// 모든 Command에는 model만 전달할 수 있다.
		// 따라서, Command에 전달할 데이터들은 모두 model에 저장한다.
		model.addAttribute("request", request);
		InsertContactCommand command = ctx.getBean("insertContactCommand", InsertContactCommand.class);
		command.execute(model);
		
		// 삽입 후에는 반드시 redirect
		return "redirect:selectContactList.do";  // 삽입 후 목록 보기로 이동 (redirect:매핑)	
	}
	
	@GetMapping(value="selectContactByNo.do")
	public String selectContactByNo(@RequestParam("no") long no,
								  Model model) {
		logger.info("selectContactByNo() 호출");
		model.addAttribute("no", no);
		ContactViewCommand command = ctx.getBean("contactViewCommand", ContactViewCommand.class);
		command.execute(model);
		return "contact/view";
	}
	
	@PostMapping(value="updateContactPage.do")
	public String updateContactPage(@ModelAttribute Contact contact) {
		logger.info("updateContactPage() 호출");
		return "contact/update";
	}
	
	@PostMapping(value="updateContact.do")
	public String updateContact(Contact contact,
							  Model model) {
		logger.info("updateContact() 호출");
		model.addAttribute("contact", contact);
		UpdateContactCommand command = ctx.getBean("updateContactCommand", UpdateContactCommand.class);
		command.execute(model);
		return "redirect:selectContactByNo.do?no=" + contact.getNo();
	}
	
	@GetMapping(value="deleteContact.do")
	public String deleteContact(@RequestParam("no") long no,
							  Model model) {
		logger.info("deleteContact() 호출");
		model.addAttribute("no", no);
		DeleteContactCommand command = (DeleteContactCommand)ctx.getBean("deleteContactCommand");
		command.execute(model);
		return "redirect:selectContactList.do";
	}
	
}
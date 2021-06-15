package com.koreait.mvc03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.mvc03.dto.Member;

@Controller
public class LoginController {

	@RequestMapping("loginPage.do")
	public String a() {
		return "member/login";
	}
	
	/*@RequestMapping("login.do")
	public String b(HttpServletRequest request,
					Model model) {
		model.addAttribute("id", request.getParameter("id"));
		model.addAttribute("pw", request.getParameter("pw"));
		return "member/loginResult";
	}*/
	
	/*@RequestMapping("login.do")
	public String c(@RequestParam("id") String id,
					@RequestParam("pw") String pw,
					Model model) {
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "member/loginResult";
	}*/
	
	@RequestMapping("login.do")
	public String d(Member member,
					Model model) {
		model.addAttribute("id", member.getId());
		model.addAttribute("pw", member.getPw());
		return "member/loginResult";
	}
	
}

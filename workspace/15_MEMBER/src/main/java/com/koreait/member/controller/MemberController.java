package com.koreait.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="joinPage.do")
	public String joinPage() {
		return "member/join";
	}
	
}

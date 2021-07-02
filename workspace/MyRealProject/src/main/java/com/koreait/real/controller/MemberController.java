package com.koreait.real.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

	private SqlSession sqlSession;
	

	public MemberController(SqlSession sqlSession) {
		super();
		
	}

	@GetMapping(value= {"/", "index.do"})
	public String index() {
		return "index";
	}
	
	
	
	
	
	
}

package com.koreait.mvc03.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.mvc03.dto.Person;

@Controller
public class MyController6 {

	/*
		전달 받은 파라미터 처리
		
		1. HttpServletRequest request
		2. @RequestParam
		3. DTO
		4. @ModelAttribute
	*/
	
	
	// 1. request 이용하기
	
	@RequestMapping("f5/v01")
	public String a(HttpServletRequest request,  // request에 name, age 파라미터
				    Model model) {
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		model.addAttribute("name", name);  // request에 name 속성
		model.addAttribute("age", age);  // request에 age 속성
		
		return "folder05/view01";  // forward : 기존 request를 보냄 
		
	}
	
	// 2. @RequestParam 이용하기
	// @RequestParam(value="파라미터")
	// @RequestParam("파라미터")
	@RequestMapping("f5/v02")
	public String b(@RequestParam("name") String name,  // @RequestParam(파라미터명) 저장할 변수
					@RequestParam("age") int age,
					Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "folder05/view02";
		
	}
	
	// 3. @RequestParam의 null 처리
	// required=false : 파라미터가 없어도 처리된다.
	// defaultValue="0" : 파라미터가 없으면 0을 사용한다.
	@RequestMapping("f5/v03")
	public String c(@RequestParam(value="name", required=false) String name,
					@RequestParam(value="age", required=false, defaultValue="0") int age,
					Model model) {
		
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "folder05/view03";
		
	}
	
	// 4. DTO 이용하기
	@RequestMapping("f5/v04")
	public String d(Person person,  // 파라미터 name, age가 person에 저장된다.
					Model model) {
		
		model.addAttribute("person", person);
		
		return "folder05/view04";
		
	}
	
	// 5. @ModelAttribute
	@RequestMapping("f5/v05")
	public String e(@ModelAttribute(value="name") String name,  // 파라미터 name을 String name에 저장한 뒤 model에 저장한다.
					@ModelAttribute("age") int age) {
		return "folder05/view05";
	}
	
	// 6. @ModelAttribute
	@RequestMapping("f5/v06")
	public String f(@ModelAttribute("person") Person person) {
		return "folder05/view06";
	}
	
	
	
	
	
	
	
}
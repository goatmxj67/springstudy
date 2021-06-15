package com.koreait.mvc03.quiz;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("quiz")
public class QuizController {

	@RequestMapping("v01")
	public String a(HttpServletRequest request,
					Model model) {
		model.addAttribute("title", request.getParameter("title"));
		model.addAttribute("hit", Integer.parseInt(request.getParameter("hit")));
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "quiz/view01";
	}
	
	@RequestMapping("v02")
	public String b(@RequestParam("title") String title,
					@RequestParam("hit") int hit,
					Model model) {
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "quiz/view02";
	}
	
	@RequestMapping("v03")
	public String c(@RequestParam(value="title", required=false, defaultValue="공지사항") String title,
					@RequestParam(value="hit", required=false, defaultValue="5") int hit,
					Model model) {
		model.addAttribute("title", title);
		model.addAttribute("hit", hit);
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "quiz/view03";
	}
	
	@RequestMapping("v04")
	public String d(Board board,
					Model model) {
		board.setDate(new Date());
		model.addAttribute("board", board);
		return "quiz/view04";
	}
	
	@RequestMapping("v05")
	public String e(@ModelAttribute("title") String title,
					@ModelAttribute("hit") int hit,
					Model model) {
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "quiz/view05";
	}
	
	@RequestMapping("v06")
	public String f(@ModelAttribute("board") Board board,
					Model model) {
		model.addAttribute("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		return "quiz/view06";
	}
	
}
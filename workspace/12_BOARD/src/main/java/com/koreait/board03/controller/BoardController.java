package com.koreait.board03.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.board03.command.InsertBoardCommand;
import com.koreait.board03.command.SelectBoardListCommand;
import com.koreait.board03.command.SelectBoardViewCommand;

@Controller
public class BoardController {
	
	// field
	// private SqlSessionTemplate sqlSession;
	
	@Autowired
	private SqlSession sqlSession;  // SqlSessionTemplate 클래스는 SqlSession 인터페이스를 구현한 클래스

	@Autowired
	private SelectBoardListCommand selectBoardListCommand;
	
	@Autowired
	private SelectBoardViewCommand selectBoardViewCommand;
	
	@Autowired
	private InsertBoardCommand insertBoardCommand;
	
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		selectBoardListCommand.execute(sqlSession, model);
		return "board/list";  // board/list.jsp로 포워드
	}
	
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(HttpServletRequest request,
								  Model model) {
		model.addAttribute("request", request);
		selectBoardViewCommand.execute(sqlSession, model);
		return "board/view";  // board/view.jsp로 포워드
	}
	
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insert";
	}
	
	@GetMapping(value="insertBoard.do")
	public String insertBoard(HttpServletRequest request,
							  Model model) {
		model.addAttribute("request", request);
		insertBoardCommand.execute(sqlSession, model);
		return "redirect:selectBoardList.do";  // selectBoardList.do로 리다이렉트
	}
	
}

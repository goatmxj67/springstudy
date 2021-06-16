package com.koreait.board01.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.board01.command.BoardCommand;
import com.koreait.board01.command.BoardListCommand;
import com.koreait.board01.command.InsertBoardCommand;

@Controller
public class BoardController {

	// field
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private BoardCommand command;
	
	// method
	@GetMapping(value="/")  // @RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		logger.info("index() 호출");
		return "index";
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		logger.info("selectBoardList() 호출");
		command = new BoardListCommand();
		command.execute(model);
		return "board/list";  // board/list.jsp로 이동
	}
	
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insert";  // board/insert.jsp로 이동
	}
	
	@GetMapping(value="insertBoard.do")
	public String insertBoard(HttpServletRequest request,  // <form> 태그 요소가 파라미터로 전달된다.
							  Model model) {
		// 모든 Command에는 model만 전달할 수 있다.
		// 따라서, Command에 전달할 데이터들은 모두 model에 저장한다.
		model.addAttribute("request", request);
		command = new InsertBoardCommand();
		command.execute(model);
		
		// 삽입 후에는 반드시 redirect
		return "redirect:selectBoardList.do";  // 삽입 후 목록 보기로 이동 (redirect:매핑)
		
	}
	
	
	
	
	
}

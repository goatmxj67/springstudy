package com.koreait.board01.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.board01.command.BoardCommand;

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
		
		return "board/list";  // board/list.jsp로 이동
	}
	
}

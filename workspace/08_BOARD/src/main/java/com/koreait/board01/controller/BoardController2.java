package com.koreait.board01.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.board01.command.BoardListCommand;
import com.koreait.board01.command.BoardViewCommand;
import com.koreait.board01.command.DeleteBoardCommand;
import com.koreait.board01.command.InsertBoardCommand;
import com.koreait.board01.command.UpdateBoardCommand;
import com.koreait.board01.dto.Board;

// @Controller
public class BoardController2 {

	// field
	private static final Logger logger = LoggerFactory.getLogger(BoardController2.class);

	// root-context.xml 정의된 bean 주입
	/*
		1. 필드 이용하기 (하나씩 모두 적용)
		
		@Autowired
		private BoardListCommand boardListCommand;
		@Autowired
		private BoardViewCommand boardViewCommand;
		...
	*/
	/*
		2. setter 형태의 메소드 이용하기.
		private BoardListCommand boardListCommand;
		private BoardViewCommand boardViewCommand;
		private InsertBoardCommand insertBoardCommand;
		private DeleteBoardCommand deleteBoardCommand;
		private UpdateBoardCommand updateBoardCommand;
		
		@Autowired
		public void setCommand(BoardListCommand boardListCommand,  -- 매개변수로 자동 주입
							   BoardViewCommand boardViewCommand,
							   InsertBoardCommand insertBoardCommand,
							   DeleteBoardCommand deleteBoardCommand,
							   UpdateBoardCommand updateBoardCommand) {
			this.boardListCommand = boardListCommand;
			this.boardViewCommand = boardViewCommand;
			this.insertBoardCommand = insertBoardCommand;
			this.deleteBoardCommand = deleteBoardCommand;
			this.updateBoardCommand = updateBoardCommand;
		}
	*/
	
	private BoardListCommand boardListCommand;
	private BoardViewCommand boardViewCommand;
	private InsertBoardCommand insertBoardCommand;
	private DeleteBoardCommand deleteBoardCommand;
	private UpdateBoardCommand updateBoardCommand;
	
	@Autowired
	public void setCommand(BoardListCommand boardListCommand,
						   BoardViewCommand boardViewCommand,
						   InsertBoardCommand insertBoardCommand,
						   DeleteBoardCommand deleteBoardCommand,
						   UpdateBoardCommand updateBoardCommand) {
		this.boardListCommand = boardListCommand;
		this.boardViewCommand = boardViewCommand;
		this.insertBoardCommand = insertBoardCommand;
		this.deleteBoardCommand = deleteBoardCommand;
		this.updateBoardCommand = updateBoardCommand;
	}
	
	// method
	@GetMapping(value="/")  // @RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		logger.info("index() 호출");
		return "index";
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		logger.info("selectBoardList() 호출");
		boardListCommand.execute(model);
		return "board/list";  // board/list.jsp로 이동
	}
	
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		logger.info("insertBoardPage() 호출");
		return "board/insert";  // board/insert.jsp로 이동
	}
	
	@GetMapping(value="insertBoard.do")
	public String insertBoard(HttpServletRequest request,  // <form> 태그 요소가 파라미터로 전달된다.
							  Model model) {
		logger.info("insertBoard() 호출");
		model.addAttribute("request", request);
		insertBoardCommand.execute(model);
		return "redirect:selectBoardList.do";  // 삽입 후 목록 보기로 이동 (redirect:매핑)	
	}
	
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(@RequestParam("no") long no,
								  Model model) {
		logger.info("selectBoardByNo() 호출");
		model.addAttribute("no", no);
		boardViewCommand.execute(model);
		return "board/view";
	}
	
	@PostMapping(value="updateBoardPage.do")
	public String updateBoardPage(@ModelAttribute Board board) {
		logger.info("updateBoardPage() 호출");
		return "board/update";
	}
	
	@PostMapping(value="updateBoard.do")
	public String updateBoard(Board board,
							  Model model) {
		logger.info("updateBoard() 호출");
		model.addAttribute("board", board);
		updateBoardCommand.execute(model);
		return "redirect:selectBoardByNo.do?no=" + board.getNo();
	}
	
	@GetMapping(value="deleteBoard.do")
	public String deleteBoard(@RequestParam("no") long no,
							  Model model) {
		logger.info("deleteBoard() 호출");
		model.addAttribute("no", no);
		deleteBoardCommand.execute(model);
		return "redirect:selectBoardList.do";
	}
	
}
package com.koreait.test1.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.koreait.test1.command.DeleteBoardCommand;
import com.koreait.test1.command.InsertBoardCommand;
import com.koreait.test1.command.SelectBoardListCommand;
import com.koreait.test1.command.SelectBoardViewCommand;
import com.koreait.test1.command.UpdateBoardCommand;

@Controller
public class BoardController {

	private SqlSession sqlSession;
	private SelectBoardListCommand selectBoardListCommand;
	private SelectBoardViewCommand selectBoardViewCommand;
	private InsertBoardCommand insertBoardCommand;
	private UpdateBoardCommand updateBoardCommand;
	private DeleteBoardCommand deleteBoardCommand;

	@Autowired
	public BoardController(SqlSession sqlSession,
			SelectBoardListCommand selectBoardListCommand,
			SelectBoardViewCommand selectBoardViewCommand,
			InsertBoardCommand insertBoardCommand,
			UpdateBoardCommand updateBoardCommand,
			DeleteBoardCommand deleteBoardCommand) {
		super();
		this.sqlSession = sqlSession;
		this.selectBoardListCommand = selectBoardListCommand;
		this.selectBoardViewCommand = selectBoardViewCommand;
		this.insertBoardCommand = insertBoardCommand;
		this.updateBoardCommand = updateBoardCommand;
		this.deleteBoardCommand = deleteBoardCommand;
	}

	@GetMapping(value = {"/", "selectBoardList.do"})
	public String list(Model model) {
		selectBoardListCommand.execute(sqlSession, model);
		return "board/list";
	}

	@GetMapping(value = "insertPage.do")
	public String insertPage() {
		return "board/insert";
	}

	@GetMapping(value = "insert.do")
	public void insert(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		insertBoardCommand.execute(sqlSession, model);
	}

	@GetMapping(value = "selectBoardByIdx.do")
	public String view(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		selectBoardViewCommand.execute(sqlSession, model);
		return "board/view";
	}

	@GetMapping(value = "updateBoard.do")
	public void update(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		updateBoardCommand.execute(sqlSession, model);
	}

	@GetMapping(value = "deleteBoard.do")
	public void delete(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		deleteBoardCommand.execute(sqlSession, model);
	}

}

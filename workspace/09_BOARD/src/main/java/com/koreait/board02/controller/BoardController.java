package com.koreait.board02.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.board02.command.SelectBoardListCommand;
import com.koreait.board02.command.SelectBoardViewCommand;

@Controller
public class BoardController {

	// field
	private SelectBoardListCommand selectBoardListCommand;
	private SelectBoardViewCommand selectBoardViewCommand;
	
	
	@Autowired
	public void setCommand(SelectBoardListCommand selectBoardListCommand, 
						   SelectBoardViewCommand selectBoardViewCommand) {
		this.selectBoardListCommand = selectBoardListCommand;
		this.selectBoardViewCommand = selectBoardViewCommand;
	}
	
	@GetMapping(value="/")
	public String index() {
		return "index";  // index.jsp로 포워드 
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		selectBoardListCommand.execute(model);
		return "board/list";  // board/list.jsp로 포워드 (model.addAttribute 처리한 속성이 넘어감)
	}
	
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(@RequestParam("no") long no,
								  Model model) {
		model.addAttribute("no", no);  // SelectBoardViewCommand에게 no를 넘겨주기 위해서
		selectBoardViewCommand.execute(model);
		return "board/view";  // board/view.jsp로 포워드 (selectBoardViewCommand가 model에 저장한 board 가지고 이동)
	}
	
	
	
	
	
	
}

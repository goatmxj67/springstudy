package com.koreait.board01.command;

import org.springframework.ui.Model;

import com.koreait.board01.dao.BoardDAO;

public class BoardListCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		
		// Model : JSP에게 값을 전달할 때 사용한다.
		
		model.addAttribute("list", BoardDAO.getInstance().selectBoardList());

	}

}

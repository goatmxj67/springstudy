package com.koreait.board01.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.koreait.board01.dao.BoardDAO;

public class BoardViewCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		long no = (Long)map.get("no");
		model.addAttribute("board", BoardDAO.getInstance().selectBoardByNo(no));		

	}

}
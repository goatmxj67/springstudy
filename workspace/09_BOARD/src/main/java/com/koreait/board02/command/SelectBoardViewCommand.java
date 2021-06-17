package com.koreait.board02.command;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.board02.dao.BoardDAO;

public class SelectBoardViewCommand implements BoardCommand {

	@Autowired
	private BoardDAO BoardDAO;
	
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		long no = (Long)map.get("no");
		model.addAttribute("board", BoardDAO.selectBoardByNo(no));
		
	}

}

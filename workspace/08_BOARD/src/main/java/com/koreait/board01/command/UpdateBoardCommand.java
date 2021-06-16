package com.koreait.board01.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.koreait.board01.dao.BoardDAO;
import com.koreait.board01.dto.Board;

public class UpdateBoardCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		Board board = (Board)map.get("board");
		BoardDAO.getInstance().updateBoard(board);

	}

}
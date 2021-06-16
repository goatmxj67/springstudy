package com.koreait.board01.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.koreait.board01.dao.BoardDAO;
import com.koreait.board01.dto.Board;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public void execute(Model model) {
		
		// model을 Map으로 변경하기
		Map<String, Object> map = model.asMap();
		
		// model에서 request빼기
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		Board board = new Board();
		board.setWriter(request.getParameter("writer"));
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		
		BoardDAO.getInstance().insertBoard(board);
		
	}

}

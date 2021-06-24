package com.koreait.file.command;

import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.file.dao.BoardDAO;
import com.koreait.file.dto.Board;

public class SelectBoardViewCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		Board board = boardDAO.selectBoardByNo(no);
		model.addAttribute("board", board);
		try { 
			model.addAttribute("filename", URLDecoder.decode(board.getFilename(), "utf-8")); 
		} catch(Exception e) { }

	}

}
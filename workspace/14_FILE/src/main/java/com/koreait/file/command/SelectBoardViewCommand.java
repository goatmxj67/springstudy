package com.koreait.file.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.file.dao.BoardDAO;

public class SelectBoardViewCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		model.addAttribute("board", boardDAO.selectBoardByNo(no));
		
	}

}

package com.koreait.test1.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.test1.dao.BoardDAO;

public class SelectBoardViewCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int bIdx = Integer.parseInt(request.getParameter("bIdx"));
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		
		model.addAttribute("boardDTO", boardDAO.selectBybIdx(bIdx));
		
	}

}

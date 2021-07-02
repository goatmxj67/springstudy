package com.koreait.real.command.board;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public interface BoardCommand {
	public void execute(SqlSession sqlSession, Model model);
}

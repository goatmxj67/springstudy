package com.koreait.board02.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import com.koreait.board02.dto.Board;

public class BoardDAO {

	// BoardDAO boardDAO bean은 스프링에 의해서 singleton으로 생성된다.
	
	@Autowired
	private JdbcTemplate template;
	private String sql;
	
	// 1. list
	public List<Board> selectBoardList() {
		sql = "SELECT NO, WRITER, TITLE, CONTENT, POSTDATE FROM BOARD";
		return template.query(sql, new BeanPropertyRowMapper<Board>(Board.class));
	}
	
	// 2. view
	public Board selectBoardByNo(long no) {
		sql = "SELECT NO, WRITER, TITLE, CONTENT, POSTDATE FROM BOARD WHERE NO = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<>(Board.class), no);
	}
	
	
	
	
	
	
	
	
}

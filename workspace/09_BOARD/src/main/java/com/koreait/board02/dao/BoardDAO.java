package com.koreait.board02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

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
	
	// 3. update
	public int updateBoard(Board board) {
		sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE NO = ?";
		 return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// 매개변수 ps를 이용해서 ?를 채움
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setLong(3, board.getNo());
			}
		});
	}
	
	// 4. delete
	public int deleteBoard(long no) {
		sql = "DELETE FROM BOARD WHERE NO = ?";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO 매개변수 ps를 이용해서 ? 채움
				ps.setLong(1, no);
			}
		});
	}
	
	// 5. insert
	public int insertBoard(Board board) {
		// PreparedStatementSetter로 작업해도 됨
		return template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO 매개변수 con으로 ps를 만들어서 반환
				String sql = "INSERT INTO BOARD VALUES (BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, board.getWriter());
				ps.setString(2, board.getTitle());
				ps.setString(3, board.getContent());
				return ps;
			}
		});
	}
	
	
	
	
}

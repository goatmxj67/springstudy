package com.koreait.integration.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.integration.domain.Board;

@Repository  // 해도 그만 안해도 그만
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public List<Board> selectAll() {
		return sqlSession.selectList("com.koreait.integration.repository.board.selectAll");
	}
	
	public List<Board> selectQuery(Map<String, String> map) {
		return sqlSession.selectList("com.koreait.integration.repository.board.selectQuery", map);
	}
	
	
	
	
	
	
	
	
	
}

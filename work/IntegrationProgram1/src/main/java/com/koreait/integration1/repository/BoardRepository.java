package com.koreait.integration1.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.integration1.domain.SearchBoard;

@Repository  // 해도 그만 안해도 그만
public class BoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int countAll() {
		return sqlSession.selectOne("com.koreait.integration1.repository.board.countAll");
	}
	
	public List<SearchBoard> selectAll() {
		return sqlSession.selectList("com.koreait.integration1.repository.board.selectAll");
	}
	
	public List<SearchBoard> selectQuery(Map<String, String> map) {
		return sqlSession.selectList("com.koreait.integration1.repository.board.selectQuery", map);
	}
	
	
	
	
	
	
	
	
	
}

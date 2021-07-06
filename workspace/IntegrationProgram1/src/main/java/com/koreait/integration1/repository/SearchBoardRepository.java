package com.koreait.integration1.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.integration1.domain.Query;
import com.koreait.integration1.domain.SearchBoard;

@Repository
public class SearchBoardRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public List<SearchBoard> searchAll() {
		return sqlSession.selectList("com.koreait.integration1.repository.searchboard.searchAll");
	}
	public List<SearchBoard> search(Query query) {
		return sqlSession.selectList("com.koreait.integration1.repository.searchboard.search", query);
	}
	
}

package com.koreait.integration1.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.koreait.integration1.repository.BoardRepository;

public class SearchTest {

	@Before
	public void setUp() throws Exception {
		//Map<String, String> map = new HashMap<String, String>();
		//map.put("TITLE", "영화");
		//map.put("TITLE", "액션");
		//BoardRepository boardRepository = new BoardRepository();
	}

	@Test
	public void test() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("TITLE", "액션");
		BoardRepository boardRepository = new BoardRepository();
		//assertNotNull(BoardRepository.class.selectAll(map), "제목에 '영화'를 포함한 결과가 없습니다.");
		assertNotNull(boardRepository.selectQuery(map), "액션 검색 결과가 없습니다.");
	}

}

package com.koreait.test1;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.test1.config.BeanConfiguration;
import com.koreait.test1.dao.BoardDAO;
import com.koreait.test1.dto.BoardDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {BeanConfiguration.class})

public class BoardTest {
	
	@Autowired
	private SqlSession sqlSession;

	
	@Before
	public void insertTest() {
		
		String bWriter = "테스트";
		String bTitle = "테스트제목";
		String bContent = "테스트내용";
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		int result = boardDAO.insertBoard(bWriter, bTitle, bContent);
		
		assertEquals("작성 실패", 1, result);
		
	}

	@After
	public void selectTest() {
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		BoardDTO board = boardDAO.selectBybIdx(9999);
		if (board == null) {
			fail("조회 실패");
		}
		
	}

	@Test
	public void updateTest() {
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		int result = boardDAO.updateBoard("변경공지사항제목", "변경공지사항제목", 9999);
		assertEquals("업데이트 실패", 1, result);
			
	}
	
	@Test
	public void deleteTest() {
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		int result = boardDAO.deleteBoard(9999);
		assertEquals("삭제 실패", 1, result);
		
	}

}

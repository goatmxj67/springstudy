package com.koreait.board03.dao;

import java.util.List;

import com.koreait.board03.dto.Board;

public interface BoardDAO {

	// BoardDAO는 board.xml과 직접 연결한다.
	// BoardDAO method == board.xml mapper id
	
	
	public List<Board> selectBoardList();
	
	public Board selectBoardByNo(long no);
	
	public void insertBoard(Board board);
	// public void insertBoard2(String writer, String title, String content);
	
	
	
	
	
	
	
}

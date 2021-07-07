package com.koreait.file.dao;

import java.util.List;

import com.koreait.file.dto.Board;

public interface BoardDAO {

	public List<Board> selectBoardList();
	public int insertBoard(Board board);
	public Board selectBoardByNo(long no);
	public int updateBoard(Board board);
	public int deleteBoard(long no);
	
}

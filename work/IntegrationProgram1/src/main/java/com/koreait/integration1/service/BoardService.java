package com.koreait.integration1.service;

import java.util.List;
import java.util.Map;

import com.koreait.integration1.domain.SearchBoard;

public interface BoardService {

	public int countList();
	public List<SearchBoard> totalList();
	public List<SearchBoard> searchList(Map<String, String> map);
	
}

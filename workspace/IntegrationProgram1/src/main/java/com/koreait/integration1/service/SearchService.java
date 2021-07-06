package com.koreait.integration1.service;

import java.util.List;

import com.koreait.integration1.domain.Query;
import com.koreait.integration1.domain.SearchBoard;

public interface SearchService {
	public List<SearchBoard> searchAll();
	public List<SearchBoard> search(Query query);	
}

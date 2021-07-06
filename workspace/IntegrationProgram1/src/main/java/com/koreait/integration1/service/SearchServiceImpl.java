package com.koreait.integration1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.integration1.domain.Query;
import com.koreait.integration1.domain.SearchBoard;
import com.koreait.integration1.repository.SearchBoardRepository;

@Service
public class SearchServiceImpl {

	@Autowired
	private SearchBoardRepository repository;
	
	public List<SearchBoard> searchAll() {
		return repository.searchAll();
	}
	
	public List<SearchBoard> search(Query query) {
		return repository.search(query);
	}
	
}

package com.koreait.integration1.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.integration1.domain.SearchBoard;
import com.koreait.integration1.repository.BoardRepository;

@Service  // 해도 그만 안해도 그만
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository repository;
	
	@Override
	public int countList() {
		return repository.countAll();
	}
	
	@Override
	public List<SearchBoard> totalList() {
		return repository.selectAll();
	}

	@Override
	public List<SearchBoard> searchList(Map<String, String> map) {
		return repository.selectQuery(map);
	}

}

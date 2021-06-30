package com.koreait.search.dao;

import java.util.List;

import com.koreait.search.dto.Employees;
import com.koreait.search.dto.PageDTO;
import com.koreait.search.dto.QueryDTO;

public interface SearchDAO {

	public int getTotalRecord();
	public List<Employees> searchAll(PageDTO pageDTO);
	public List<Employees> autoComplete(QueryDTO queryDTO);
	
}

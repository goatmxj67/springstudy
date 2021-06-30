package com.koreait.search.dao;

import java.util.List;

import com.koreait.search.dto.Employees;
import com.koreait.search.dto.PageDTO;

public interface SearchDAO {

	public int getTotalRecord();
	public List<Employees> searchAll(PageDTO pageDTO);
	
}

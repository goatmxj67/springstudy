package com.koreait.search.command;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.search.dao.SearchDAO;
import com.koreait.search.dto.Employees;
import com.koreait.search.dto.PageDTO;
import com.koreait.search.util.PagingUtils;

public class SearchAllCommand implements SearchCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		SearchDAO searchDAO = sqlSession.getMapper(SearchDAO.class);
		int totalRecord = searchDAO.getTotalRecord();
		
		PageDTO pageDTO = PagingUtils.getPage(totalRecord, page);
		
		List<Employees> list = searchDAO.searchAll(pageDTO);
		String paging = PagingUtils.getPaging("searchAll.do", page);
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);

	}

}
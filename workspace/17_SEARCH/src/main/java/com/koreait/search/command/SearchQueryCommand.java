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
import com.koreait.search.dto.QueryDTO;
import com.koreait.search.util.PagingUtils;

public class SearchQueryCommand implements SearchCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		Optional<String> opt = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt.orElse("1"));
		
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		String top = request.getParameter("top");
		String bottom = request.getParameter("bottom");
		
		QueryDTO queryDTO = new QueryDTO();
		queryDTO.setColumn(column);
		queryDTO.setQuery(query);
		queryDTO.setTop(top);
		queryDTO.setBottom(bottom);
		
		SearchDAO searchDAO = sqlSession.getMapper(SearchDAO.class);
		int searchRecord = searchDAO.getSearchRecord(queryDTO);
		
		PageDTO pageDTO = PagingUtils.getPage(searchRecord, page);
		
		queryDTO.setBeginRecord(pageDTO.getBeginRecord());
		queryDTO.setEndRecord(pageDTO.getEndRecord());
		
		List<Employees> list = searchDAO.search(queryDTO);
		String paging = null;
		if (column.equals("SALARY")) {
			paging = PagingUtils.getPaging("search.do?column=" + column + "&bottom=" + bottom + "&top=" + top, page);
		} else {
			paging = PagingUtils.getPaging("search.do?column=" + column + "&query=" + query, page);
		}
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		
	}

}

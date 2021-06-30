package com.koreait.search.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.springframework.ui.Model;

import com.koreait.search.dao.SearchDAO;
import com.koreait.search.dto.Employees;
import com.koreait.search.dto.QueryDTO;

public class AutoCompleteCommand implements SearchCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		QueryDTO queryDTO = (QueryDTO)map.get("queryDTO");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		SearchDAO searchDAO = sqlSession.getMapper(SearchDAO.class);
		List<Employees> list = searchDAO.autoComplete(queryDTO);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("list", null);
		} else {
			resultMap.put("status", 200);
			resultMap.put("list", list);
		}
		
		// 응답 : 요청한 jsp로 응답이 이루어지므로 index.jsp로 응답된다.
		// index.jsp에서 ajax로 요청했으므로 index.jsp의 success로 응답된다.
		try {
			response.setContentType("text/html; charset=utf-8");
			JSONObject obj = new JSONObject(resultMap);
			response.getWriter().println(obj.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
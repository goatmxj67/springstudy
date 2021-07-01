package com.koreait.integration.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.integration.domain.Board;
import com.koreait.integration.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="selectAll.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectAll() {
		List<Board> list = boardService.totalList();
		Map<String, Object> resultMap = new HashMap<>();
		if (list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("message", "목록이 없습니다.");
		} else {
			resultMap.put("status", 200);
			resultMap.put("list", list);
			resultMap.put("message", "목록을 가져왔습니다.");
		}
		return resultMap;
	}
	
	@GetMapping(value="selectQuery.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectQuery(HttpServletRequest request) {
		Map<String, String> map = new HashMap<>();
		map.put("column", request.getParameter("column"));
		map.put("query", request.getParameter("query"));
		List<Board> list = boardService.searchList(map);
		Map<String, Object> resultMap = new HashMap<>();
		if (list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("list", null);
			resultMap.put("message", "검색결과가 없습니다.");
		} else {
			resultMap.put("status", 200);
			resultMap.put("list", list);
			resultMap.put("message", "검색결과를 가져왔습니다.");
		}
		return resultMap;
	}
	
	
	
	
	
	
	
	
	
}

package com.koreait.integration1.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.integration1.domain.Query;
import com.koreait.integration1.domain.SearchBoard;
import com.koreait.integration1.service.SearchServiceImpl;

@Controller
public class SearchBoardController {

	@Autowired
	private SearchServiceImpl searchService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping(value = "searchAll.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> searchAll() {
		List<SearchBoard> list = searchService.searchAll();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("message", "목록이 없습니다.");
			resultMap.put("list", null);
		} else {
			resultMap.put("status", 200);
			resultMap.put("message", "전체 " + list.size() + "개의 목록을 가져왔습니다.");
			resultMap.put("list", list);
		}
		return resultMap;
	}

	@GetMapping(value = "search.do", produces = "application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> search(Query query) {
		List<SearchBoard> list = searchService.search(query);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (list.size() == 0) {
			resultMap.put("status", 500);
			resultMap.put("message", query.getSearchText() + " 검색 결과가 없습니다.");
			resultMap.put("list", null);
		} else {
			resultMap.put("status", 200);
			resultMap.put("message", list.size() + "개의 검색 결과가 있습니다.");
			resultMap.put("list", list);
		}
		return resultMap;
	}

}

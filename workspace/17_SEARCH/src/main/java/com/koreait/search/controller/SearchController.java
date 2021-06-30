package com.koreait.search.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.search.command.AutoCompleteCommand;
import com.koreait.search.command.SearchAllCommand;
import com.koreait.search.dto.QueryDTO;

@Controller
public class SearchController {

	private static final Logger logger = LoggerFactory.getLogger(SearchController.class); 
	private SqlSession sqlSession;
	private SearchAllCommand searchAllCommand;
	private AutoCompleteCommand autoCompleteCommand;
	
	@Autowired
	public SearchController(SqlSession sqlSession,
							SearchAllCommand searchAllCommand,
							AutoCompleteCommand autoCompleteCommand) {
		super();
		this.sqlSession = sqlSession;
		this.searchAllCommand = searchAllCommand;
		this.autoCompleteCommand = autoCompleteCommand;
	}

	@GetMapping(value = {"/", "index.do"})
	public String index() {
		logger.info("call index()");
		return "index";
	}
	
	@GetMapping(value = "searchAll.do")
	public String searchAll(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		searchAllCommand.execute(sqlSession, model);
		return "list";  // SearchAllCommand가 model에 저장한 정보를 가지고 list.jsp로 포워드
	}
	
	@PostMapping(value="autoComplete.do")
	@ResponseBody
	public void autoComplete(@RequestBody QueryDTO queryDTO,
							 HttpServletResponse response,
							 Model model) {
		logger.info(queryDTO.toString());
		model.addAttribute("response", response);
		model.addAttribute("queryDTO", queryDTO);
		autoCompleteCommand.execute(sqlSession, model);
	}
	
	
	
	
	
	
	
	
	
	
}

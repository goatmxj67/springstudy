package com.team.d.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.d.command.board.ShowBoardCommand;
import com.team.d.command.reply.GetReplyListCommand;
import com.team.d.command.reply.InsertReplyCommand;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class ReplyController {

	private SqlSession sqlSession;
	private ShowBoardCommand showBoardCommand;
	private InsertReplyCommand insertReplyCommand;
	private GetReplyListCommand getReplyListCommand;
	
	@PostMapping(value="insertReply.do")
	public String insertReply(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		insertReplyCommand.execute(sqlSession, model);
		showBoardCommand.execute(sqlSession, model);
		return "board/selectBoard";
	}
	
	@GetMapping(value="getReplyList.do", produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> getReplyList(HttpServletRequest request, Model model) {
		model.addAttribute("request", request);
		return getReplyListCommand.execute(sqlSession, model);
	}
}
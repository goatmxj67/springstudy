package com.koreait.ajax.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.ajax.command.DeleteMemberCommand;
import com.koreait.ajax.command.InsertMemberCommand;
import com.koreait.ajax.command.SelectMemberListCommand;
import com.koreait.ajax.command.SelectMemberViewCommand;
import com.koreait.ajax.command.UpdateMemberCommand;
import com.koreait.ajax.dto.Member;
import com.koreait.ajax.dto.Page;

@Controller
public class MemberController {

	// field
	private SqlSession sqlSession;
	private InsertMemberCommand insertMemberCommand;
	private SelectMemberListCommand selectMemberListCommand;
	private SelectMemberViewCommand selectMemberViewCommand;
	private UpdateMemberCommand updateMemberCommand;
	private DeleteMemberCommand deleteMemberCommand;
	
	// constructor
	@Autowired
	public MemberController(SqlSession sqlSession, InsertMemberCommand insertMemberCommand,
			SelectMemberListCommand selectMemberListCommand, SelectMemberViewCommand selectMemberViewCommand,
			UpdateMemberCommand updateMemberCommand, DeleteMemberCommand deleteMemberCommand) {
		super();
		this.sqlSession = sqlSession;
		this.insertMemberCommand = insertMemberCommand;
		this.selectMemberListCommand = selectMemberListCommand;
		this.selectMemberViewCommand = selectMemberViewCommand;
		this.updateMemberCommand = updateMemberCommand;
		this.deleteMemberCommand = deleteMemberCommand;
	}

	@GetMapping(value= {"/", "index.do"})
	public String index() {
		return "index";
	}
	
	@GetMapping(value="manageMember.do")
	public String manageMember() {
		return "member/manageMember";
	}
	
	@GetMapping(value="manageMemberRest.do")
	public String manageMemberRest() {
		return "member/manageMemberRest";
	}
	
	@PostMapping(value="insertMember.do",
			 	 produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> insertMember(@RequestBody Member member,
											HttpServletResponse response,
											Model model) {
		model.addAttribute("member", member);
		model.addAttribute("response", response);
		return insertMemberCommand.execute(sqlSession, model);
	}

	@PostMapping(value="selectMemberList.do",
				 produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectMemberList(@RequestBody Page page,
												Model model) {
		model.addAttribute("page", page.getPage());
		return selectMemberListCommand.execute(sqlSession, model);
	}

	@PostMapping(value="selectMemberByNo.do",
				 produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectMemberByNo(@RequestBody Member member,
												Model model) {
		model.addAttribute("no", member.getNo());
		return selectMemberViewCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value="updateMember.do",
				 produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> updateMember(@RequestBody Member member,
											Model model) {
		model.addAttribute("member", member);
		return updateMemberCommand.execute(sqlSession, model);
	}
	
	@PostMapping(value="deleteMember.do",
				 produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> deleteMember(@RequestBody Member member,
											Model model) {
		model.addAttribute("no", member.getNo());
		return deleteMemberCommand.execute(sqlSession, model);
	}
	
}

package com.team.d.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.d.command.member.EmailAuthCommand;
import com.team.d.command.member.EmailCheckCommand;
import com.team.d.command.member.FindIdCommand;
import com.team.d.command.member.FindPwCommand;
import com.team.d.command.member.IdCheckCommand;
import com.team.d.command.member.JoinCommand;
import com.team.d.command.member.LeaveCommand;
import com.team.d.command.member.LoginCommand;
import com.team.d.command.member.LogoutCommand;
import com.team.d.command.member.PresentPwCheckCommand;
import com.team.d.command.member.UpdateMemberCommand;
import com.team.d.command.member.UpdatePwCommand;
import com.team.d.dto.MemberDTO;

@Controller
public class MemberController {

	// field
	private SqlSession sqlSession;
	private LoginCommand loginCommand;
	private LogoutCommand logoutCommand;
	private IdCheckCommand idCheckCommand;
	private EmailCheckCommand emailCheckCommand;
	private EmailAuthCommand emailAuthCommand;
	private JoinCommand joinCommand;
	private PresentPwCheckCommand presentPwCheckCommand;
	private UpdatePwCommand updatePwCommand;
	private UpdateMemberCommand updateMemberCommand;
	private FindIdCommand findIdCommand;
	private FindPwCommand findPwCommand;
	private LeaveCommand leaveCommand;

	// constructor
	@Autowired
	public MemberController(SqlSession sqlSession,
							LoginCommand loginCommand,
							LogoutCommand logoutCommand,
							IdCheckCommand idCheckCommand,
							EmailCheckCommand emailCheckCommand,
							EmailAuthCommand emailAuthCommand,
							JoinCommand joinCommand,
							PresentPwCheckCommand presentPwCheckCommand,
							UpdatePwCommand updatePwCommand,
							UpdateMemberCommand updateMemberCommand,
							FindIdCommand findIdCommand,
							FindPwCommand findPwCommand,
							LeaveCommand leaveCommand) {
		super();
		this.sqlSession = sqlSession;
		this.loginCommand = loginCommand;
		this.logoutCommand = logoutCommand;
		this.idCheckCommand = idCheckCommand;
		this.emailCheckCommand = emailCheckCommand;
		this.emailAuthCommand = emailAuthCommand;
		this.joinCommand = joinCommand;
		this.presentPwCheckCommand = presentPwCheckCommand;
		this.updatePwCommand = updatePwCommand;
		this.updateMemberCommand = updateMemberCommand;
		this.findIdCommand = findIdCommand;
		this.findPwCommand = findPwCommand;
		this.leaveCommand = leaveCommand;
	}
	
	// 메인페이지 index.jsp 단순이동
	@GetMapping(value= {"/", "index.do"})
	public String index() {
		return "index";
	}
	// 로그인 페이지 login.jsp 단순이동
	@GetMapping(value="loginPage.do")
	public String loginPage() {
		return "member/login";
	}
	// 마이페이지 myPage.jsp 단순이동
	@GetMapping(value="myPage.do")
	public String myPage() {
		return "member/myPage";
	}
	// 회원가입 페이지 join.jsp 단순이동
	@GetMapping(value="joinPage.do")
	public String joinPage() {
		return "member/join";
	}
	/*// 아이디 찾기 페이지 findId.jsp 단순이동
	@GetMapping(value="findIdPage.do")
	public String findIdPage() {
		return "member/findId";
	}
	// 비밀번호 찾기 페이지 findPw.jsp 단순이동
	@GetMapping(value="findPwPage.do")
	public String findPwPage() {
		return "member/findPw";
	}*/
	// 아이디/비밀번호 찾기 findIdAndPw.jsp 단순이동
	@GetMapping(value="findIdAndPwPage.do")
	public String findIdAndPwPage() {
		return "member/findIdAndPw";
	}
	
	// 로그인(login)
	@PostMapping(value="login.do")
	public void login(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		loginCommand.execute(sqlSession, model);
	}
	// 로그아웃(logout)
	@GetMapping(value="logout.do")
	public String logout(HttpSession session, Model model) {
		model.addAttribute("session", session);
		logoutCommand.execute(sqlSession, model);
		return "redirect:/";
	}
	// 아이디 중복체크(idCheck) 
	@ResponseBody // AJAX매핑
	@GetMapping(value="idCheck.do", produces="application/json; charset=utf-8") // 중복 체크 후 JSON형태로 반환
	public Map<String, Object> idCheck(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		return idCheckCommand.execute(sqlSession, model);
	}
	// 이메일 중복체크(emailCheck) 
	@ResponseBody // AJAX매핑
	@GetMapping(value="emailCheck.do", produces="application/json; charset=utf-8") // 중복 체크 후 JSON형태로 반환
	public Map<String, Object> emailCheck(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		return emailCheckCommand.execute(sqlSession, model);
	}
	// 이메일 인증코드 받기(emailCode)
	@ResponseBody // AJAX매핑
	@GetMapping(value="emailCode.do", produces="application/json; charset=utf-8") // 인증코드 확인 후 JSON형태로 반환
	public Map<String, String> emailCode(HttpServletRequest request, Model model){
		model.addAttribute("request", request);
		return emailAuthCommand.execute(sqlSession, model);
	}
	// 회원가입(join)
	@PostMapping(value="join.do")
	public void join(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		joinCommand.execute(sqlSession, model);
	}
	// 현재 비밀번호 확인(presentPwCheck) : 오류
	@ResponseBody // AJAX매핑
	@PostMapping(value="presentPwCheck.do", produces="application/json; charset=utf-8") // 현재 비밀번호 확인 후 JSON형태로 반환
	public Map<String, Boolean> presentPwCheck(@RequestBody MemberDTO memberDTO, HttpSession session, Model model){ // 파라미터 없이 JSON 객체 전달 받을 때 @RequestBody 사용								  
		model.addAttribute("session", session);
		model.addAttribute("memberDTO", memberDTO);
		return presentPwCheckCommand.execute(sqlSession, model);
	}
	// 비밀번호 변경(updatePw) : 오류
	@PostMapping(value="updatePw.do")
	public void updatePw(MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		updatePwCommand.execute(sqlSession, model);
	}
	// 회원정보 변경(updateMember)
	@PostMapping(value="updateMember.do")
	public void updateMember(MemberDTO memberDTO, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("memberDTO", memberDTO);
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		updateMemberCommand.execute(sqlSession, model);
	}
	// 아이디 찾기(findId)
	@ResponseBody // AJAX매핑
	@PostMapping(value="findId.do", produces="application/json; charset=utf-8")
	public Map<String, Object> findId(@RequestBody MemberDTO memberDTO, Model model) { 
		model.addAttribute("memberDTO", memberDTO);
		return findIdCommand.execute(sqlSession, model);
	}
	// 새 비밀번호 변경 페이지 changePw.jsp로 파라미터값 가지고 이동
	@PostMapping(value="changePwPage.do")
	public String changePwPage(@ModelAttribute("mEmail") String mEmail) {
		return "member/changePw";
	}
	// 비밀번호 찾기&변경(changePw)
	@PostMapping(value="changePw.do")
	public void changePw(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("request", request);
		model.addAttribute("response", response);
		findPwCommand.execute(sqlSession, model);
	}
	// 회원탈퇴(leave)
	@GetMapping(value="leave.do")
	public void leave(HttpSession session, HttpServletResponse response, Model model) {
		model.addAttribute("session", session);
		model.addAttribute("response", response);
		leaveCommand.execute(sqlSession, model);
	}
	
}
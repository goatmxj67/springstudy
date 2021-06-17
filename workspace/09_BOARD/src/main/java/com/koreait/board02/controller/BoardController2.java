package com.koreait.board02.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.koreait.board02.command.DeleteBoardCommand;
import com.koreait.board02.command.InsertBoardCommand;
import com.koreait.board02.command.SelectBoardListCommand;
import com.koreait.board02.command.SelectBoardViewCommand;
import com.koreait.board02.command.UpdateBoardCommand;
import com.koreait.board02.config.BeanConfiguration;
import com.koreait.board02.dto.Board;

@Controller
public class BoardController2 {

	// field
	private AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfiguration.class);
	
	@GetMapping(value="/")
	public String index() {
		return "index";  // index.jsp로 포워드 
	}
	
	@GetMapping(value="selectBoardList.do")
	public String selectBoardList(Model model) {
		SelectBoardListCommand selectBoardListCommand = ctx.getBean("listCommand", SelectBoardListCommand.class);
		selectBoardListCommand.execute(model);
		return "board/list";  // board/list.jsp로 포워드 (model.addAttribute 처리한 속성이 넘어감)
	}
	
	@GetMapping(value="selectBoardByNo.do")
	public String selectBoardByNo(@RequestParam("no") long no,
								  Model model) {
		model.addAttribute("no", no);  // SelectBoardViewCommand에게 no를 넘겨주기 위해서
		SelectBoardViewCommand selectBoardViewCommand = ctx.getBean("viewCommand", SelectBoardViewCommand.class);
		selectBoardViewCommand.execute(model);
		return "board/view";  // board/view.jsp로 포워드 (selectBoardViewCommand가 model에 저장한 board 가지고 이동)
	}
	
	@PostMapping(value="updateBoardPage.do")
	public String updateBoardPage(Board board, Model model) {
		model.addAttribute("board", board);
		return "board/update";  // board/update.jsp로 포워드
	}
	
	@PostMapping(value="updateBoard.do")
	public String updateBoard(HttpServletRequest request,  // update.jsp에서 전달한 파라미터
							  Model model) {
		model.addAttribute("req", request);  // UpdateBoardCoammand에게 전달하기 위해서
		UpdateBoardCommand updateBoardCommand = ctx.getBean("updateCommand", UpdateBoardCommand.class);
		updateBoardCommand.execute(model);
		return "redirect:selectBoardByNo.do?no=" + request.getParameter("no");  // selectBoardByNo.do 매핑으로 리다이렉트(삽입, 수정, 삭제)
	}
	
	@GetMapping(value="deleteBoard.do")
	public String deleteBoard(@RequestParam("no") long no,  // 삭제할 게시글 no
			  				  Model model) {
		model.addAttribute("no", no);  // DeleteBoardCommand에게 전달하기 위해서
		DeleteBoardCommand deleteBoardCommand = ctx.getBean("deleteCommand", DeleteBoardCommand.class);
		deleteBoardCommand.execute(model);
		// return "redirect:selectBoardList.do";  // 삭제 후 목록 보기로 이동
		return selectBoardList(model);
	}
	
	@GetMapping(value="insertBoardPage.do")
	public String insertBoardPage() {
		return "board/insert";
	}
	
	@GetMapping(value="insertBoard.do")
	public String insertBoard(Board board,
							  Model model) {
		model.addAttribute("board", board);  // InsertBoardCommand에 전달하기 위해서
		InsertBoardCommand insertBoardCommand = ctx.getBean("insertCommand", InsertBoardCommand.class);
		insertBoardCommand.execute(model);
		return "redirect:selectBoardList.do";
	}
	
}

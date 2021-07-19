package com.team.d.command.member;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team.d.dao.MemberDAO;
import com.team.d.dto.MemberDTO;
import com.team.d.util.SecurityUtils;

public class LoginCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		HttpSession session = request.getSession();
		
		// request를 통해 입력된 mId, mPw가 memberDTO에 mId, mPw와 일치하는지 확인
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMId(request.getParameter("mId"));
		memberDTO.setMPw(SecurityUtils.encodeBase64(request.getParameter("mPw"))); // 입력된 비밀번호 암호화 처리
		
		// memberDAO의 로그인 login메소드 호출
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO loginUser = memberDAO.login(memberDTO);

		try{
			response.setContentType("text/html; charset=utf-8");
			if(loginUser != null){ // 로그인 성공 시 session에 등록
				session.setAttribute("loginUser", loginUser);
				response.getWriter().append("<script>");
				response.getWriter().append("location.href='loginPage.do';");
				response.getWriter().append("</script>");
			} else{
				response.getWriter().append("<script>");
				response.getWriter().append("alert('가입된 정보가 없습니다. 아이디와 비밀번호를 확인하세요!');");
				response.getWriter().append("location.href='index.do'");
				response.getWriter().append("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
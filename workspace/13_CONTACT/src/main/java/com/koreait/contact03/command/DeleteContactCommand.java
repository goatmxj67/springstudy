package com.koreait.contact03.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.contact03.dao.ContactDAO;

public class DeleteContactCommand implements ContactCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		
		long no = Long.parseLong(request.getParameter("no"));

		ContactDAO contactDAO = sqlSession.getMapper(ContactDAO.class);
		int count = contactDAO.deleteContact(no);

		response.setContentType("text/html; charset=utf-8");
		try {
			if (count >  0) {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('연락처가 삭제되었습니다.')");
				response.getWriter().println("location.href='selectContactList.do'");
				response.getWriter().println("</script>");
			} else {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('연락처가 삭제되지 않았습니다.')");
				response.getWriter().println("history.back()");
				response.getWriter().println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
package com.koreait.contact01.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact01.dao.ContactDAO;

public class DeleteContactCommand implements ContactCommand {

	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		
		long no = Long.parseLong(request.getParameter("no"));

		response.setContentType("text/html; charset=utf-8");
		int count = contactDAO.deleteContact(no);
		try {
			if (count >  0) {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('연락처가 삭제되었습니다.')");
				response.getWriter().println("location.href='selectContactList.do'");  // 여기서 redirect 이루어짐
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
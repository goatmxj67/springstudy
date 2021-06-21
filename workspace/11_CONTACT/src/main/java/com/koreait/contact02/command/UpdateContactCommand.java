package com.koreait.contact02.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.koreait.contact02.dao.ContactDAO;
import com.koreait.contact02.dto.Contact;

public class UpdateContactCommand implements ContactCommand {

	@Autowired
	private ContactDAO contactDAO;
	
	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		HttpServletResponse response = (HttpServletResponse) map.get("response");
		
		Contact contact = new Contact();
		contact.setNo(Long.parseLong(request.getParameter("no")));
		contact.setName(request.getParameter("name"));
		contact.setTel(request.getParameter("tel"));
		contact.setAddr(request.getParameter("addr"));
		contact.setEmail(request.getParameter("email"));
		contact.setNote(request.getParameter("note"));

		response.setContentType("text/html; charset=utf-8");
		int count = contactDAO.updateContact(contact);
		try {
			if (count >  0) {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('연락처가 수정되었습니다.')");
				response.getWriter().println("location.href='selectContactView.do?no=" + request.getParameter("no") + "'");  // 여기서 redirect 이루어짐
				response.getWriter().println("</script>");
			} else {
				response.getWriter().println("<script>");
				response.getWriter().println("alert('연락처가 수정되지 않았습니다.')");
				response.getWriter().println("history.back()");
				response.getWriter().println("</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
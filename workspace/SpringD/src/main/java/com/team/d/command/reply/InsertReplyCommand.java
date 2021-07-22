package com.team.d.command.reply;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team.d.dao.ReplyDAO;
import com.team.d.dto.ReplyDTO;

public class InsertReplyCommand implements ReplyCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		long bIdx = Long.parseLong(request.getParameter("bIdx"));
		String rContent = request.getParameter("reply");
		String mId = request.getParameter("mId");
		ReplyDTO dto = new ReplyDTO();
		dto.setBIdx(bIdx);
		dto.setRContent(rContent);
		dto.setMId(mId);
		dto.setParent(0);
		
		ReplyDAO dao = sqlSession.getMapper(ReplyDAO.class);
		dao.insertReply(dto);
		
	}

}
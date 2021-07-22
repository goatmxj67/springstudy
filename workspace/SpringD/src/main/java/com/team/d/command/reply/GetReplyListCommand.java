package com.team.d.command.reply;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team.d.dao.ReplyDAO;

public class GetReplyListCommand  {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		long bIdx = Long.parseLong(request.getParameter("bIdx"));
		
		ReplyDAO dao = sqlSession.getMapper(ReplyDAO.class);
		Map<String, Object> resultMap = new HashMap<>();
		
		resultMap.put("list", dao.getReplyListByBidx(bIdx));
		System.out.println(resultMap.get("list"));
		return resultMap;
	}

}
package com.koreait.ajax.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;
import com.koreait.ajax.dto.Member;
import com.koreait.ajax.dto.Page;
import com.koreait.ajax.util.PagingUtils;

public class SelectMemberListCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		int page = (int)map.get("page");
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		int totalRecord = memberDAO.getTotalMemberCount();
		
		Page paging = PagingUtils.getPage(totalRecord, page);
		
		List<Member> list = memberDAO.selectMemberList(paging);
		// System.out.println("회원 수: " + list.size());
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("exists", list.size() > 0);
		resultMap.put("paging", paging);
		return resultMap;
		
	}

}

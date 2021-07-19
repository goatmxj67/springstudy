package com.team.d.command.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team.d.dao.MemberDAO;
import com.team.d.dto.MemberDTO;

public class FindIdCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		
		// 아이디 찾기 시 request에 입력된 mName, mEmail 확인
		String mName = request.getParameter("mName");
		String mEmail = request.getParameter("mEmail");
		
		// memberDTO에 mName, mEmail이 일치하는지 확인
		MemberDTO memberDTO = new MemberDTO();
		memberDTO.setMId(mName);
		memberDTO.setMEmail(mEmail);
		
		// memberDAO의 아이디 찾기 findId메소드 호출
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO findUser = memberDAO.findId(memberDTO);
		
		// 일치하는 mId 반환
		String mId = findUser.getMId();

		Map<String, Object> resultMap = new HashMap<>();
		if(mId == null) { // 입력한 정보와 일치하는 mId가 없을 경우
			resultMap.put("status", 500);
			resultMap.put("mId", null);
		} else {
			resultMap.put("status", 200);
			resultMap.put("mId", mId);
		}
		return resultMap;

	}

}
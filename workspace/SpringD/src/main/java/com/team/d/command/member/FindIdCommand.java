package com.team.d.command.member;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.team.d.dao.MemberDAO;
import com.team.d.dto.MemberDTO;

public class FindIdCommand {

	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MemberDTO memberDTO = (MemberDTO)map.get("memberDTO");
		
		// 아이디 찾기 시 request에 입력된 mName, mEmail 확인
		// String mName = request.getParameter("mName");
		// String mEmail = request.getParameter("mEmail");
	
		// memberDTO에 mName, mEmail이 일치하는지 확인
		// memberDTO.setMId(mName);
		// memberDTO.setMEmail(mEmail);
		
		// memberDAO의 아이디 찾기 findId메소드 호출
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		MemberDTO findUser = memberDAO.findId(memberDTO);

		Map<String, Object> resultMap = new HashMap<>();
		if(findUser == null) { // 입력한 정보와 일치하는 mId가 없을 경우
			resultMap.put("status", 500);
		} else {
			resultMap.put("status", 200);
			resultMap.put("mId", findUser.getMId());
		}
		return resultMap;

	}

}
package com.koreait.member.dao;

import com.koreait.member.dto.Member;

public interface MemberDAO {

	public int idCheck(String id);
	public int join(Member member);
	
}

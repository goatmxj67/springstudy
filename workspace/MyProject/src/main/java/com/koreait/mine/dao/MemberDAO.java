package com.koreait.mine.dao;

import com.koreait.mine.dto.Member;

public interface MemberDAO {

	public int idCheck(String id);
	public int join(Member member);
	public Member login(Member member);
	public int leave(long no);
	public int changeState(long no);
	public int updateMember(Member member);
	public int updatePw(Member member);
	public Member findId(String email);
	public int changePw(Member member);
	
}

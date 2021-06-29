package com.koreait.ajax.dao;

import java.util.List;

import org.springframework.dao.DuplicateKeyException;

import com.koreait.ajax.dto.Member;
import com.koreait.ajax.dto.Page;

public interface MemberDAO {
	public int insertMember(Member member) throws DuplicateKeyException;
	public int getTotalMemberCount();
	public List<Member> selectMemberList(Page paging);
	public Member selectMemberByNo(long no);
	public int updateMember(Member member);
	public int deleteMember(long no);
}

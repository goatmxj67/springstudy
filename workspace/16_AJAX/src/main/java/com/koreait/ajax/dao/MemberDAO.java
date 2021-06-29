package com.koreait.ajax.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DuplicateKeyException;

import com.koreait.ajax.dto.Member;

public interface MemberDAO {
	public int insertMember(Member member) throws DuplicateKeyException;
	public int getTotalMemberCount();
	public List<Member> selectMemberList(Map<String, Integer> map);
}

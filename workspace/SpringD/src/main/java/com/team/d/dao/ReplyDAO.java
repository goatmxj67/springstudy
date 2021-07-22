package com.team.d.dao;

import java.util.List;

import com.team.d.dto.ReplyDTO;

public interface ReplyDAO {

	public void insertReply(ReplyDTO dto);

	public List<ReplyDTO> getReplyListByBidx(long BIDX);
	
}
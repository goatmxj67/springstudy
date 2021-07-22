package com.team.d.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReplyDTO {

	private long rIdx;
	private String rContent;
	private String mId;
	private long parent;
	private long bIdx;
	private String rPostDate;
	
}

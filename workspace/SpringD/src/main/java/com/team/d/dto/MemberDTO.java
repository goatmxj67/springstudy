package com.team.d.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class MemberDTO {

	// field
	private long mNo;
	private String mName;
	private String mId;
	private String mPw;
	private String mEmail;
	private String mPhone;
	private String mGrade;
	private Date mRegdate;
	
}
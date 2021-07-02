package com.koreait.mine.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class Member {

	private long no;
	private String id;
	private String pw;
	private String name;
	private String phone;
	private String email;
	private String address;
	private Date regdate;
	private int state;
	
}

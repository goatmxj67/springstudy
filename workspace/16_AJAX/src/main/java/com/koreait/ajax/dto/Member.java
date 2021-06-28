package com.koreait.ajax.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {

	private long no;
	private String id;
	private String name;
	private String address;
	private String gender;
	
}

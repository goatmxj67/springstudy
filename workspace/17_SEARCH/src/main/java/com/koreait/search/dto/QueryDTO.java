package com.koreait.search.dto;

import lombok.Data;

@Data
public class QueryDTO {
	private String column;
	private String query;
	private String top;
	private String bottom;
	private int beginRecord;
	private int endRecord;
}

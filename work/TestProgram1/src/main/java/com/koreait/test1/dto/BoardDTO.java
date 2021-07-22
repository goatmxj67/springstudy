package com.koreait.test1.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BoardDTO {
	private int bIdx;
	private String bWriter;
	private String bTitle;
	private String bContent;
	private Date bDate;
}

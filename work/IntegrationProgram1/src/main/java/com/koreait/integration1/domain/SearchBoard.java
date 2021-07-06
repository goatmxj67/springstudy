package com.koreait.integration1.domain;

import java.sql.Date;

public class SearchBoard {

	private long no;
	private String title;
	private String content;
	private Date regdate;
	
	public SearchBoard() {
		super();
	}

	public SearchBoard(long no, String title, String content, Date regdate) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
	
}

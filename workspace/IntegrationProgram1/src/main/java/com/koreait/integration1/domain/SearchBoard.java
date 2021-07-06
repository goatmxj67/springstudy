package com.koreait.integration1.domain;

import java.sql.Date;

public class SearchBoard {

	private int no;
	private String title;
	private String content;
	private Date regdate;
	
	public SearchBoard() {
		super();
	}
	public SearchBoard(int no, String title, String content, Date regdate) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
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

package com.koreait.file.dto;

import java.sql.Date;

public class Board {
	
	private long no;
	private String writer; 
	private String title;
	private String content;
	private String ip;
	private String origin_filename;
	private String save_filename;
	private Date postdate;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOrigin_filename() {
		return origin_filename;
	}
	public void setOrigin_filename(String origin_filename) {
		this.origin_filename = origin_filename;
	}
	public String getSave_filename() {
		return save_filename;
	}
	public void setSave_filename(String save_filename) {
		this.save_filename = save_filename;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	@Override
	public String toString() {
		return "Board [no=" + no + ", writer=" + writer + ", title=" + title + ", content=" + content + ", ip=" + ip
				+ ", origin_filename=" + origin_filename + ", save_filename=" + save_filename + ", postdate=" + postdate
				+ "]";
	}
	
}

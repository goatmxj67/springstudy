package com.koreait.integration1.domain;

public class Query {

	private String column;
	private String searchText;
	
	public Query() {
		super();
	}
	public Query(String column, String searchText) {
		super();
		this.column = column;
		this.searchText = searchText;
	}
	
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
}

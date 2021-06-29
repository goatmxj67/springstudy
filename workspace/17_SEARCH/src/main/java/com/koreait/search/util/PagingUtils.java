package com.koreait.search.util;

import com.koreait.search.dto.Page;

public class PagingUtils {

	// field
	public static int recordPerPage = 5;
	public static int pagePerBlock = 3;
	
	// ajax에서 사용
	public static Page getPage(int totalRecord, int page) {
		
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = endRecord < totalRecord ? endRecord : totalRecord;
		
		int totalPage = (totalRecord / recordPerPage) + (totalRecord % recordPerPage > 0 ? 1 : 0);
		int beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		int endPage = beginPage + pagePerBlock - 1;
		endPage = endPage < totalPage ? endPage : totalPage;
		
		Page paging = new Page();
		paging.setPage(page);
		paging.setTotalRecord(totalRecord);
		paging.setRecordPerPage(recordPerPage);
		paging.setBeginRecord(beginRecord);
		paging.setEndRecord(endRecord);
		paging.setTotalPage(totalPage);
		paging.setPagePerBlock(pagePerBlock);
		paging.setBeginPage(beginPage);
		paging.setEndPage(endPage);
		
		return paging;
		
	}
	
	// mvc 사용
	
	
}

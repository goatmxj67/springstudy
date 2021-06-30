package com.koreait.search.util;

import com.koreait.search.dto.PageDTO;

public class PagingUtils {

	// field
	public static int recordPerPage = 10;
	public static int beginRecord;
	public static int endRecord;
	
	public static int pagePerBlock = 5;
	public static int totalPage;
	public static int beginPage;
	public static int endPage;
	
	public static PageDTO getPage(int totalRecord, int page) {
		
		beginRecord = (page - 1) * recordPerPage + 1;
		endRecord = beginRecord + recordPerPage - 1;
		endRecord = endRecord < totalRecord ? endRecord : totalRecord;
		
		totalPage = (totalRecord / recordPerPage) + (totalRecord % recordPerPage > 0 ? 1 : 0);
		beginPage = ((page - 1) / pagePerBlock) * pagePerBlock + 1;
		endPage = beginPage + pagePerBlock - 1;
		endPage = endPage < totalPage ? endPage : totalPage;
		
		PageDTO pageDTO = new PageDTO();
		pageDTO.setPage(page);
		pageDTO.setTotalRecord(totalRecord);
		pageDTO.setRecordPerPage(recordPerPage);
		pageDTO.setBeginRecord(beginRecord);
		pageDTO.setEndRecord(endRecord);
		pageDTO.setTotalPage(totalPage);
		pageDTO.setPagePerBlock(pagePerBlock);
		pageDTO.setBeginPage(beginPage);
		pageDTO.setEndPage(endPage);
		
		return pageDTO;
		
	}
	
	// << 1 2 3 4 5 >> 반환
	public static String getPaging(String path, int page) {
		
		StringBuilder sb = new StringBuilder();
		
		if (beginPage <= pagePerBlock) {
			sb.append("◀&nbsp;");
		} else {
			if (path.indexOf("?") > 0) {
				sb.append("<a href=\"" + path + "&page=" + (beginPage - 1) + "\">◀</a>&nbsp;");
			} else {
				sb.append("<a href=\"" + path + "?page=" + (beginPage - 1) + "\">◀</a>&nbsp;");				
			}
		}
		
		for (int p = beginPage; p <= endPage; p++) {
			if (p == page) {
				sb.append(p + "&nbsp;");
			} else {
				if (path.indexOf("?") > 0) {
					sb.append("<a href=\"" + path + "&page=" + p + "\">" + p + "</a>&nbsp;");
				} else {
					sb.append("<a href=\"" + path + "?page=" + p + "\">" + p + "</a>&nbsp;");					
				}
			}
		}
		
		if (endPage == totalPage) {
			sb.append("▶");
		} else {
			if (path.indexOf("?") > 0) {
				sb.append("<a href=\"" + path + "&page=" + (endPage + 1) + "\">▶</a>");
			} else {
				sb.append("<a href=\"" + path + "?page=" + (endPage + 1) + "\">▶</a>");				
			}
		}
		
		return sb.toString();
		
	}
	
}
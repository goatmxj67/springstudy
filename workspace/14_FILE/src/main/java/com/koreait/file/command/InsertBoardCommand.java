package com.koreait.file.command;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.koreait.file.dao.BoardDAO;
import com.koreait.file.dto.Board;

public class InsertBoardCommand implements BoardCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest)map.get("multipartRequest");
		
		Board board = new Board();
		board.setWriter(multipartRequest.getParameter("writer"));
		board.setTitle(multipartRequest.getParameter("title"));
		board.setContent(multipartRequest.getParameter("content"));
		board.setIp(multipartRequest.getRemoteAddr());
		
		MultipartFile file = multipartRequest.getFile("file");
		
		try {
		
			if (file != null && !file.isEmpty()) {  // 둘 다 필요

				String origin_filename = file.getOriginalFilename();
				
				// 서버에 저장할 파일명
				// UUID : Universally Unique IDentifier - 범용 고유 식별자
				// 550e8400-e29b-41d4-a716-446655440000 : 하이픈(-) 제외 32자리
				String extension = origin_filename.substring( origin_filename.lastIndexOf(".") );
				String save_filename = UUID.randomUUID().toString().replaceAll("-", "") + extension;
				
				// 첨부파일을 저장할 서버 위치
				String realPath = multipartRequest.getServletContext().getRealPath("resources/archive");  // archive 디렉터리는 없으므로 생성이 필요
				
				// archive 디렉터리 생성
				File archive = new File(realPath);
				if ( !archive.exists() ) {
					archive.mkdirs();
				}
				
				// 서버에 첨부파일 저장
				File save_file = new File(archive, save_filename);
				try {
					file.transferTo(save_file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				board.setOrigin_filename(origin_filename);
				board.setSave_filename(save_filename);
				
			} else {
				board.setOrigin_filename("");
				board.setSave_filename("");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		// DB에 데이터 저장
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		boardDAO.insertBoard(board);
		
	}

}

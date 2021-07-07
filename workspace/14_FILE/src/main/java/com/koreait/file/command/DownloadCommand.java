package com.koreait.file.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;

import com.koreait.file.dao.BoardDAO;
import com.koreait.file.dto.Board;

public class DownloadCommand implements BoardCommand {
	
	@Override
	public void execute(SqlSession sqlSession, Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest)map.get("request");
		HttpServletResponse response = (HttpServletResponse)map.get("response");
		
		String realPath = request.getServletContext().getRealPath("resources/archive");

		long no = Long.parseLong(request.getParameter("no"));
		
		BoardDAO boardDAO = sqlSession.getMapper(BoardDAO.class);
		Board board = boardDAO.selectBoardByNo(no);
		
		// 다운로드 할 File 생성
		File download_file = new File(realPath, board.getSave_filename());  // 서버에서 찾아야 하는 파일명을 작성
		
		// 스트림 준비
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			
			// response 다운로드 처리
			response.setHeader("Content-Type", "applicaion/x-msdownload");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(board.getOrigin_filename(), "utf-8").replaceAll("\\+", " "));
			response.setHeader("Content-Length", download_file.length() + "");
			
			// 서버에 저장된 download할 파일을 읽는 스트림
			bis = new BufferedInputStream(new FileInputStream(download_file));
			
			// 사용자에게 파일을 생성하는 출력 스트림
			bos = new BufferedOutputStream(response.getOutputStream());
			
			// bis로 읽은 내용을 bos로 전송(파일 복사 진행)
			FileCopyUtils.copy(bis, bos);
			
			// 혹시 남은 게 있다면
			bos.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bos != null) bos.close();
				if (bis != null) bis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

package com.koreait.integration1.batch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.koreait.integration1.domain.SearchBoard;
import com.koreait.integration1.repository.BoardRepository;

public class SearchJob implements Job {
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		/*
		String arr[] = {"코미디", "공포", "멜로", "드라마", "SF"};
		String choice = arr[(int)Math.random()*5];
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("TITLE", choice);
		
		BoardRepository boardRepository = new BoardRepository();
		List<SearchBoard> searchResult = boardRepository.selectQuery(map);
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("검색결과.txt"));
			for (int i = 0; i < searchResult.size(); i++) {
				bw.write((i + 1) + ", ");
				bw.write(searchResult.get(i).getTitle() + ", ");
				bw.write(searchResult.get(i).getContent() + ", ");
				bw.write(searchResult.get(i).getRegdate() + "\n");
			}
			System.out.println("검색결과.txt 파일이 생성되었습니다.");
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e1) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("error.txt"));
				bw.write(e1.getMessage() + "\t" + "검색 결과가 없습니다.");
				bw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		*/
		System.out.println("SearchJob 검색 완료!");
	}

}

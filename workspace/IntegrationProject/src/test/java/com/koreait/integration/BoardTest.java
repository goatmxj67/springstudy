package com.koreait.integration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Date;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

public class BoardTest {

	@Test
	public void test() {
		
		// 제목에 공지가 포함된 게시글을 검색
		// 다음 주소를 이용해서 테스트를 수행하시오.
		// 검색되지 않는 경우 message를 출력하시오.
		
		try {
			String query = URLEncoder.encode("공지","utf-8");
			String apiURL = "http://localhost:9090/integration/selectQuery.do?column=TITLE&query=" + query;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			BufferedReader br = new BufferedReader( new InputStreamReader( con.getInputStream() ) );
			String resultMap = "";
			String line = null;
			while ( (line = br.readLine()) != null ) {
				resultMap += line;
			}
			// System.out.println(resultMap);
			
			// JSON문자열 -> JSONObject : org.json JSON
			JSONObject obj = new JSONObject(resultMap);
			
			// 1. list
			JSONArray arr = obj.getJSONArray("list");
			for (int i = 0; i < arr.length(); i++) {
				JSONObject board = (JSONObject) arr.get(i);
				System.out.println("번호: " + board.getInt("no"));
				System.out.println("제목: " + board.getString("title"));
				System.out.println("내용: " + board.getString("content"));
				System.out.println("작성자: " + board.getString("writer"));
				System.out.println("작성일: " + new Date((long)board.get("postdate")));  // timestamp -> new Date
			}
			
			// 2. message
			String message = obj.getString("message");
			System.out.println("메시지: " + message);
			
			// 3. status
			int status = obj.getInt("status");
			System.out.println("상태: " + status);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

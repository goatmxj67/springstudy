package com.koreait.integration1.batch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONObject;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SearchJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			String[] arr = {"코미디", "공포", "멜로", "드라마", "SF"};
			String searchText = URLEncoder.encode(arr[(int)(Math.random() * arr.length)], "utf-8");
			String apiURL = "http://localhost:9090/integration1/search.do?column=TITLE&searchText=" + searchText;
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String resultMap = "";
			String line = null;
			while ( (line = br.readLine()) != null) {
				resultMap += line;
			}
			JSONObject obj = new JSONObject(resultMap);
			if (obj.getInt("status") == 500) {
				try (BufferedWriter bw = new BufferedWriter(new FileWriter("error.txt"))) {
					bw.write(obj.getString("message"));
					bw.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

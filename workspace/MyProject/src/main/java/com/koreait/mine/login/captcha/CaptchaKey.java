package com.koreait.mine.login.captcha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;


// 네이버 캡차 API 예제 - 키발급
public class CaptchaKey {

	// 기존의 main() 메소드를
	// 발급 받은 캡차 키를 반환하는 메소드로 변경
	// 1. 반환타입 : String
	// 2. 메소드명: getCaptchaKey
	// 3. 매개변수 : 없음
    public static String getCaptchaKey() {
    	
        String clientId = "MoFkByIlbWTpHbUYkrks"; //애플리케이션 클라이언트 아이디값";
        String clientSecret = "DMcG_45yy1"; //애플리케이션 클라이언트 시크릿값";

        String code = "0"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code;

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);
        // System.out.println(responseBody);
        // responseBody : {"key":"67ANaZxlcTTIcaCS"}
        
        JSONObject obj = new JSONObject(responseBody);
        return obj.getString("key");  // return "67ANaZxlcTTIcaCS";

    }

    private static String get(String apiUrl, Map<String, String> requestHeaders){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }

    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }

    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);

        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}

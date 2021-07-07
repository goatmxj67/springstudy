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

import javax.servlet.http.HttpServletRequest;


// 네이버 캡차 API 예제 - 키발급, 키 비교
public class CaptchaValidation {

	// 기존의 main() 메소드를
	// 사용자 입력 값 검증 결과를 반환하는 메소드로 변환
	// 1. 반환타입 : boolean
	// 2. 메소드명 : getValidate
	// 3. 매개변수 : HttpServletRequest request (parameter인 user_key + session에서 key를 빼기 위해서)
	
    public static boolean getValidate(HttpServletRequest request) {
    	
        String clientId = "MoFkByIlbWTpHbUYkrks";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "DMcG_45yy1";//애플리케이션 클라이언트 시크릿값";

        String code = "1"; // 키 발급시 0,  캡차 이미지 비교시 1로 세팅
        
        try {
        	request.setCharacterEncoding("utf-8");
        } catch (Exception e) {
			e.printStackTrace();
		}

        String key = request.getSession().getAttribute("key").toString(); // 캡차 키 발급시 받은 키값
        System.out.println("캡차 키 발급시 받은 키값 : " + key);
        String value = request.getParameter("user_key1"); // 사용자가 입력한 캡차 이미지 글자값
        System.out.println("사용자 입력: " + value);
        String apiURL = "https://openapi.naver.com/v1/captcha/nkey?code=" + code + "&key=" + key + "&value=" + value;
        
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("X-Naver-Client-Id", clientId);
        requestHeaders.put("X-Naver-Client-Secret", clientSecret);
        String responseBody = get(apiURL, requestHeaders);

        System.out.println(responseBody);
        
        // responseBody : {"result":true, "responseTime":50.5}
        JSONObject obj = new JSONObject(responseBody);
        
        return (boolean)obj.get("result");  // return true;

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

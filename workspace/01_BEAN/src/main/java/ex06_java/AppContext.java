package ex06_java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public Student student() {
		List<Integer> scores = new ArrayList<Integer>();
		scores.add(90);
		scores.add(95);
		scores.add(100);
		Set<String> volunteers = new HashSet<String>();
		volunteers.add("고아원");
		volunteers.add("양로원");
		Map<String, String> home = new HashMap<String, String>();
		home.put("전화", "02-123-4567");
		home.put("주소", "서울시 마포구 공덕동");
		Student s = new Student();
		s.setName("데이빗");
		s.setScores(scores);
		s.setVolunteers(volunteers);
		s.setHome(home);
		return s;
	}
	
}

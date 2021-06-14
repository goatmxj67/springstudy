package com.koreait.mvc03.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.mvc03.dto.Person;

@Configuration
public class BeanConfiguration {

	@Bean
	public Person man() {
		Person m = new Person();
		m.setName("버터");
		m.setAge(35);
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("게임");
		hobbies.add("도박");
		m.setHobbies(hobbies);
		return m;
	}
	
	@Bean
	public Person woman() {
		Person w = new Person();
		w.setName("공주");
		w.setAge(25);
		List<String> hobbies = new ArrayList<String>();
		hobbies.add("네일");
		hobbies.add("헤어");
		w.setHobbies(hobbies);
		return w;
	}
	
}

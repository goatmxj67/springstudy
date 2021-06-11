package com.koreait.mvc02.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration 애너테이션 사용을 위해서 CGLIB 디펜던시를 추가해야 한다.

@Configuration
public class BeanConfiguration {

	@Bean
	public Contact contact3() {
		return new Contact("010-3333-3333", "서울시 강남구 논현동");
	}
	
	@Bean
	public Member member3() {
		return new Member("alice", contact3());
	}
	
}

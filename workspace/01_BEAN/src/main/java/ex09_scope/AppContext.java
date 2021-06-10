package ex09_scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppContext {

	@Bean
	@Scope(value="prototype")  // @Scope("prototype")와 같다.
	public Person person2() {
		return new Person("데이빗", 30);
	}
	
}
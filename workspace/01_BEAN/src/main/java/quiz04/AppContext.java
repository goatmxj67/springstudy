package quiz04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public Lecture lecture2() {
		return new Lecture("공학수학", "김수학");
	}
	
	@Bean
	public Student student2() {
		return new Student("저학생", lecture2());
	}
	
}
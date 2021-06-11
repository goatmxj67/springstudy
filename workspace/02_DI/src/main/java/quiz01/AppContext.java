package quiz01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public Calculator calculator() {
		return new Calculator();
	}
	
	@Bean
	public EngineerCalculator engineerCalculator() {
		return new EngineerCalculator();
	}
	
}

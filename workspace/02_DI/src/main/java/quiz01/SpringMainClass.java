package quiz01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		EngineerCalculator e = ctx.getBean("engineerCalculator", EngineerCalculator.class);
		e.add(10, 5);
		e.subtract(10, 5);
		e.multiply(10, 5);
		e.divide(10, 5);
		
		ctx.close();

	}

}

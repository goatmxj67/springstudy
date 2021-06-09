package quiz03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("quiz03.xml");
		
		Student student = ctx.getBean("student", Student.class);
		student.info();
		
		ctx.close();

	}

}
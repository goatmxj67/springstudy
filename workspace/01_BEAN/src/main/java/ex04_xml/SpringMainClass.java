package ex04_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		String resourceLocations = "app-context4.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		Member m = ctx.getBean("member", Member.class);
		m.info();
		
		ctx.close();

	}

}

package ex07_java_into_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context7.xml");
		
		Book b1 = ctx.getBean("book1", Book.class);
		Book b2 = ctx.getBean("book2", Book.class);
		
		b1.info();
		b2.info();
		
		ctx.close();

	}

}

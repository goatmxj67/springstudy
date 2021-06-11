package quiz02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		TV tv = ctx.getBean("tv", TV.class);
		tv.volUp();
		tv.volDown();
		
		ctx.close();

	}

}

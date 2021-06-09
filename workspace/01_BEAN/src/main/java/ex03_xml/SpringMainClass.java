package ex03_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		String resourceLocation1 = "app-context3-1.xml";
		String resourceLocation2 = "app-context3-2.xml";
		String resourceLocation3 = "app-context3-3.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocation1, resourceLocation2, resourceLocation3);

		ListBean listBean = ctx.getBean("listBean", ListBean.class);
		listBean.info();
		
		SetBean setBean = ctx.getBean("setBean", SetBean.class);
		setBean.info();
		
		MapBean mapBean = ctx.getBean("mapBean", MapBean.class);
		mapBean.info();
		
		ctx.close();
		
	}

}

package ex01_inject;

import javax.inject.Inject;
import javax.inject.Named;
/*
	@Inject
	
	1. 객체의 타입(<bean class="">)이 일치하는 객체를 자동으로 주입한다.
	2. 필드, 생성자, setter를 대상으로 한다.
	
*/
public class SelectListCommand {

	// 1. 필드를 이용해서 주입하기
	/*
	@Inject
	private Dao dao;
	*/
	
	
	// 2. 생성자를 이용해서 주입하기
	/*
	private Dao dao;
	
	@Inject
	public SelectListCommand(Dao dao) {  // 매개변수 Dao dao에 주입된다.
		this.dao = dao;
	}
	*/
	
	// 3. setter를 이용한 주입하기 (주로 쓰는 방식이다. -> command같은경우 여러개 있기때문에 모두 한번에 주입 가능)
	/*
	private Dao dao;
	
	@Inject
	public void setDao(Dao dao) {  // 매개변수 Dao dao에 주입된다.
		this.dao = dao;
	}
	*/
	
	// field
	private Dao dao;
	
	// contructor
	public SelectListCommand() {}
	
	// setter
	@Inject
	@Named("dao1")  // <bean id="dao1"> 인 bean을 생성하라.
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	// getter
	public Dao getDao() {
		return dao;
	}
	
	public void execute() {
		dao.selectList();  // dao가 자동으로 주입되지 않았다면 NullPointerException 발생
	}
	
}

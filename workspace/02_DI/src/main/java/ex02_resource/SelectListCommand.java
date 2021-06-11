package ex02_resource;

import javax.annotation.Resource;

/*
	@Resource
	
	1. 객체의 이름(<bean id="">)이 일치하는 객체를 자동으로 주입한다.
	2. 필드, setter를 대상으로 한다. (생성자 불가)
*/

public class SelectListCommand {
	
	// 1. field를 이용한 주입
	/*
	@Resource
	private Dao boardDao;
	*/
	
	// 2. setter를 이용한 주입
	// field
	private Dao boardDao;
	
	// setter
	@Resource
	public void setBoardDao(Dao boardDao) {
		this.boardDao = boardDao;
	}
	// getter
	public Dao getBoardDao() {
		return boardDao;
	}
	
	public void execute() {
		boardDao.selectList();
	}
	
}

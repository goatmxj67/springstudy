package quiz04;

public class Lecture {

	private String name;
	private String professor;
	
	public Lecture() {}
	public Lecture(String name, String professor) {
		super();
		this.name = name;
		this.professor = professor;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfessor() {
		return professor;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	
}
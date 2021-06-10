package quiz05;

public class HoneyMoon {

	private String city;
	private Person husband;
	private Person wife;
	
	public HoneyMoon() {}
	public HoneyMoon(String city, Person husband, Person wife) {
		super();
		this.city = city;
		this.husband = husband;
		this.wife = wife;
	}
	
	public void info() {
		System.out.println("신혼여행지: " + city);
		System.out.println("신랑명: " + husband.getName() +"(" + husband.getGender() + ")");
		System.out.println("신부명: " + wife.getName() + "(" + wife.getGender() + ")");
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Person getHusband() {
		return husband;
	}
	public void setHusband(Person husband) {
		this.husband = husband;
	}
	public Person getWife() {
		return wife;
	}
	public void setWife(Person wife) {
		this.wife = wife;
	}
	
}
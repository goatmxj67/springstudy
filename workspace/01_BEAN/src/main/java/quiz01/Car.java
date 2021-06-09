package quiz01;

public class Car {

	private String model;
	private int price;
	
	public Car(String model, int price) {
		super();
		this.model = model;
		this.price = price;
	}
	
	public void info() {
		System.out.println("자동차 모델: " + model);
		System.out.println("자동차 가격: " + price + "만원");
	}
	
}

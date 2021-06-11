package quiz01;

import org.springframework.beans.factory.annotation.Autowired;

public class EngineerCalculator {

	private Calculator calculator;
	
	public EngineerCalculator() {}
	public EngineerCalculator(Calculator calculator) {
		super();
		this.calculator = calculator;
	}
	
	public void add(int a, int b) {
		System.out.println(calculator.add(a, b));
	}
	public void subtract(int a, int b) {
		System.out.println(calculator.subtract(a, b));
	}
	public void multiply(int a, int b) {
		System.out.println(calculator.multiply(a, b));
	}
	public void divide(int a, int b) {
		System.out.println(calculator.divide(a, b));
	}
	
	public Calculator getCalculator() {
		return calculator;
	}
	@Autowired
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
}

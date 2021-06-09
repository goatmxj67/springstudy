package quiz02;

public class MultiplicationTable {

	// field(property)
	private int startDan;
	private int endDan;
	private int startNum;
	private int endNum;
	private Calculator calculator;
	
	public void info() {
		for (int dan = startDan; dan <= endDan; dan++) {
			for (int n = startNum; n <= endNum; n++) {
				System.out.println(dan + "x" + n + "=" + calculator.multiply(dan, n));
			}
			System.out.println();
		}
	}
	
	public int getStartDan() {
		return startDan;
	}
	public void setStartDan(int startDan) {
		this.startDan = startDan;
	}
	public int getEndDan() {
		return endDan;
	}
	public void setEndDan(int endDan) {
		this.endDan = endDan;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public Calculator getCalculator() {
		return calculator;
	}
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
}

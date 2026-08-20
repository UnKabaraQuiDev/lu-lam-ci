public class Calculator
{
	private double currentValue;
	
	private int operations;

	public Calculator(double start) {
		this.currentValue = start;
	}
	
	public void initialize() {
		currentValue = 0; 
		operations = 0;
	}
	
	public void add(double num) {
		currentValue += num;
		operations++;
	}
	public void subtract(double num) {
		currentValue -= num;
		operations++;
	}
	public void multiplyBy(double num) {
		currentValue *= num;
		operations++;
	}
	public void divideBy(double num) {
		currentValue /= num;
		operations++;	
	}
	
	public double getCurrentValue() {
		return currentValue;
	}
	public int getOperations() {
		return operations;
	}
}
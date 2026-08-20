public class SimpleCalculationsWithOneInt
{

	private int n;

	public SimpleCalculationsWithOneInt(int n) {
		this.n = Math.abs(n);
	}

	public void printCountUp() {
		for(int i = 0; i <= n; i++)
			System.out.println(i);
	}
	public void printCountDown() {
		for(int i = n; i >= 0; i--)
			System.out.println(i);
	}

	public long calculateSum() {
		long sum = 0;
		for(int i = 0; i <= n; i++)
			sum += i;
		return sum;
	}
	public double calculateFactorial() {
		double product = 1;
		for(int i = 2; i <= n; i++)
			product *= i;
		return product;
	}
	
}
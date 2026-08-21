public class SimpleCalculationsWithOneInt_2
{

	private int n;

	public SimpleCalculationsWithOneInt_2(int n) {
		this.n = Math.abs(n);
	}

	public void printCountUp() {
		int i = 0;
		while(i <= n)
			System.out.println(i++);
	}
	public void printCountDown() {
		int i = n;
		while(i >= n)
			System.out.println(i--);
	}

	public long calculateSum() {
		long sum = 0;
		int i = 0;
		while(i <= n)
			sum += (i++);
		return sum;
	}
	public double calculateFactorial() {
		double product = 1;
		int i = 2;
		while(i <= n)
			product *= (i++);
		return product;
	}
	
}
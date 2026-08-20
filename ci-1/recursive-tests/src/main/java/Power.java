
public class Power {

	public static double power(double x, int n) {
		return (n < 0 ? 1.0 / power(x, -n) : (n == 0 ? 1 : x * power(x, n - 1)));
	}

	public static void main(String[] args) {
		System.out.println(power(2, -4));
		assert power(2, -4) == Math.pow(2, -4);
		System.out.println(power(2, 4));
		System.out.println(power(2, 2));
		System.out.println(power(2, 1));
	}

}

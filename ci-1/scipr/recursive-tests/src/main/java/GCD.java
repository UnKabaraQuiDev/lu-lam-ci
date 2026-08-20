
public class GCD {

	public static double gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}

	public static void main(String[] args) {
		System.out.println(gcd(2, 2));
		System.out.println(gcd(16, 8));
	}

}

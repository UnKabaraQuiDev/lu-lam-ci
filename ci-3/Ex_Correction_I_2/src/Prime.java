public class Prime {

	public static boolean isPrime(int x) {

		if (x <= 1) {
			return false;
		}
		
		for (int i = 2; i <= Math.sqrt(x); i++) {
			if (x % i == 0) {
				return false;
			}
		}
		
		return true;

	}
	
	public static void main(String[] args) {
		System.out.println(isPrime(11));
		System.out.println(isPrime(9));
		System.out.println(isPrime(1234567891));
	}

}

package integeranalyse;

public final class IntegerAnalyser {

	public static boolean isEven(int num) {
		return num % 2 == 0;
	}

	public static boolean isPrime(int number) {
		if (number <= 1) {
			return false;
		}
		double sqrt = Math.sqrt(number);
		for (int i = 2; i <= sqrt; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}

	public static int sumOfDividers(int number) {
		if (number <= 1) {
			return 1;
		}
		int sum = 0;
		for (int i = 1; i <= number; i++) {
			if (number % i == 0) {
				sum += i;
			}
		}
		// System.out.println("sum : "+number+" > "+sum);
		return sum;
	}

	public static boolean isDeficient(int num) {
		return sumOfDividers(num) < 2 * num;
	}

	public static boolean isPerfect(int num) {
		return sumOfDividers(num) == 2 * num;
	}

	public static boolean isAbundant(int num) {
		return sumOfDividers(num) > 2 * num;
	}

	public static boolean isFriendlyTo(int num, int num2) {
		return sumOfDividers(num) == sumOfDividers(num2);
	}

	public static int reverse(int num) {
		return Integer.parseInt(new StringBuilder(String.valueOf(num)).reverse().toString());
	}

	public static boolean isPalindrome(int num) {
		return reverse(num) == num;
	}

}

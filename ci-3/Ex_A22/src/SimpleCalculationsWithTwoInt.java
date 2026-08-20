public class SimpleCalculationsWithTwoInt {

	private int a, b;

	public SimpleCalculationsWithTwoInt(int a, int b) {
		this.a = Math.abs(a);
		this.b = Math.abs(b);
	}

	public void printAll() {
		for(int i = a; i <= b; i++)
			System.out.println(i);
	}

	public long calculateSumEven() {
		long sum = 0;
		for(int i = a; i <= b; i++)
			if(i % 2 == 0)
				sum += i;
		return sum;
	}

	public double calculatePower() {
		return Math.pow(a, b);
	}

	public void swap() {
		int x = a;
		a = b;
		b = x;
	}

	public int GCD_Search() {
		int min = Math.min(a, b);
		int max = Math.max(a, b);
		int divisor = min;
		while(!(min % divisor == 0 && max % divisor == 0)) {
			divisor--;
			if(divisor == 0)
				return 0;
		}
		return divisor;
	}

	public int GCD_OptimizedEuclid() {
		int min = Math.min(a, b);
		int max = Math.max(a, b);
		int ia = max, ib = min;
		while(ia != 0) {
			int la = ia;
			ia = ib%la;
			ib = la;
		}
		return ib;
	}
	
	public int gcd_Euclid() {
		int min = Math.min(a, b);
		int max = Math.max(a, b);
		int divisor = min;
		int rest = max % min;
		while(rest != 0) {
			int ldiv = divisor;
			divisor = rest;
			rest = ldiv % rest;
		}
		return divisor;
	}

	public int lcm_Search() {
		int min = Math.min(a, b);
		int max = Math.max(a, b);
		int mul = max;
		while(!(mul % max == 0 && mul % min == 0)) {
			mul++;
		}
		return mul;
	}

	public int lcm_Shortcut() {
		return a*b/GCD_OptimizedEuclid();
	}
	
	public static void main(String[] args) {
		SimpleCalculationsWithTwoInt n = new SimpleCalculationsWithTwoInt(100, 50);
		n.printAll();
		System.out.println(n.lcm_Search());
	}
	
}
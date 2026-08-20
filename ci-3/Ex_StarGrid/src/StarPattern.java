
public class StarPattern {
	
	public static final String star = "*";
	
	public static void printGrid(int n) {
		
		for(int i = 0; i < n; i++) {
			for(int l = 0; l < n; l++) {
				
				System.out.print(star);
				
			}
			
			System.out.println();
		}
		
	}
	
	public static void printStairs(int n) {
		
		for(int i = 0; i < n; i++) {
			for(int l = 0; l < i+1; l++) {
				
				System.out.print(star);
				
			}
			
			System.out.println();
		}
		
	}
	
	public static void printInvertedStairs(int n) {
		
		for(int i = 0; i < n; i++) {
			for(int l = 0; l < n-i; l++) {
				
				System.out.print(star);
				
			}
			
			System.out.println();
		}
		
	}
	
	public static void printMirroredStairs(int n) {
		
		for(int line = 0; line < n; line++) {
			for(int i = 0; i < n*2; i++) {
				if(i >= n-line && i <= n+line)
					System.out.print(star);
				else
					System.out.print(" ");
			}
			
			System.out.println();
		}
		
	}
	
	public static void printMirroredStairsBorder(int n) {
		
		for(int line = 0; line < n; line++) {
			for(int i = 0; i < n*2; i++) {
				if(i == n-line || i == n+line)
					System.out.print(star);
				else
					System.out.print(" ");
			}
			
			System.out.println();
		}
		
	}
	
	public static void printNumberGrid(int n) {
		
		for(int i = 0; i < n; i++) {
			for(int l = 0; l < n; l++) {
				
				System.out.print((i+l)%n+1);
				
			}
			
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) {
		printGrid(3);
		System.out.println();
		printGrid(5);
		
		System.out.println();
		printStairs(3);
		System.out.println();
		printStairs(5);
		
		System.out.println();
		printInvertedStairs(3);
		System.out.println();
		printInvertedStairs(5);
		
		System.out.println();
		printMirroredStairs(3);
		System.out.println();
		printMirroredStairs(5);
		
		System.out.println();
		printMirroredStairsBorder(3);
		System.out.println();
		printMirroredStairsBorder(5);
		
		System.out.println();
		printNumberGrid(3);
		System.out.println();
		printNumberGrid(5);
	}
	
}

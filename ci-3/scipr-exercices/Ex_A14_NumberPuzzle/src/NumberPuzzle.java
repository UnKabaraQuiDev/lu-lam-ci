import java.util.*;

public class NumberPuzzle {
	
	private int target = 0, lastGuess = 0, count = 0;

	public void selectSecretNumber(int n) {
		this.target = (int) Math.rint(Math.random() * n);
		this.count = 0;
	}
	public String guess(int guess) {
		this.lastGuess = guess;
		count++;
		return getMessage();
	}

	private String getCountString() {
		int mod = count % 10;
		String ending = null;
		if(count >= 10 && count <= 20)
			ending = "th";
		else if(mod == 1)
			ending = "st";
		else if(mod == 2)
			ending = "nd";
		else if(mod == 3)
			ending = "rd";
		else
			ending = "th";
		return count+ending;
	}

	public String getMessage() {
		if(count == 0)
			return "You haven't entered anything";
		return (lastGuess < target ? "Your number is too small" :
			(lastGuess > target ? "Your number is too big" :
			(lastGuess == target ? "Well done! You found the secret number at the "+getCountString()+" guess" : null)));
	}

	public int getCount() {return count;}
	
	public static void main(String[] args) {
		NumberPuzzle np = new NumberPuzzle();
		System.out.println("Guess the number:");
		np.selectSecretNumber(30);

		Scanner sc = new Scanner(System.in);
		while(true) {
			if(sc.hasNextInt()) {
				System.out.println(np.guess(sc.nextInt()));
			}else if(sc.hasNext()) {
				if("q".equals(sc.next())) {
					System.out.println("Quit.");
					break;
				}
			}
		}
		sc.close();
		System.out.println("Ended.");
	}
}
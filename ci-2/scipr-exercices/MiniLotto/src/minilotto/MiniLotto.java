package minilotto;

import java.util.Arrays;

public class MiniLotto {

	private int number1, number2, number3;

	public MiniLotto(int number1, int number2, int number3) {
		this.number1 = number1;
		this.number2 = number2;
		this.number3 = number3;
	}

	public MiniLotto() {
		this.generateNumbers();

		System.out.println(this);
	}

	public void generateNumbers() {
		number1 = genRandom();
		number2 = genOtherRandom(-1, number1);
		number3 = genOtherRandom(number1, number2);
	}

	public boolean contains(int x) {
		return number1 == x || number2 == x || number3 == x;
	}

	public int play(int a, int b, int c) {
		if (Arrays.stream(new int[]{a, b, c}).distinct().count() != 3) {
			System.out.println("Erreur, il faut indiquer 3 nombres différents !");
			return -1;
		}

		int matchCount = (int) Arrays.stream(new int[]{a, b, c}).filter(this::contains).count();
		System.out.println("Vous avez deviné correctement " + matchCount + " nombre(s) !");

		return matchCount;
	}

	public static void main(String[] args) {
		/*for (int i = 0; i < 1000; i++) {
			System.out.println(new MiniLotto());
		}*/

		new MiniLotto().play(1, 2, 3);
	}

	private int genRandom() {
		return (int) ((Math.random() * 49)) + 1;
	}

	private int genOtherRandom(int a, int b) {
		int rand;
		do {
			rand = genRandom();
		} while (rand == a || rand == b);
		return rand;
	}

	@Override
	public String toString() {
		return "[" + number1 + ", " + number2 + ", " + number3 + "]";
	}

}

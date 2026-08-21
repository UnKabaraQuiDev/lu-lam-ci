package qsd;

public class Misspelling {
	final String word;
	final int position;

	Misspelling(String word, int position) {
		this.word = word;
		this.position = position;
	}

	@Override
	public String toString() {
		return word + " @ " + position;
	}
}
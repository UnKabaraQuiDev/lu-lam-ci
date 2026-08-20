package randomwords;

import java.util.Arrays;
import java.util.stream.Collectors;
import lu.pcy113.pclib.PCUtils;

public class Word {

	private String realWord;
	private String displayWord;

	public Word(String real) {
		System.out.println(real);
		this.realWord = real;
		this.displayWord = Arrays.stream(PCUtils.<String>shuffle(real.split("|"))).collect(Collectors.joining());
	}

	public String getDisplayWord() {
		return displayWord;
	}

	public String getRealWord() {
		return realWord;
	}

	public void setDisplayWord(String displayWord) {
		this.displayWord = displayWord;
	}
	
	public void unshuffle(int i) {
		for (int try_ = 0; try_ < i;) {
			String oldDisplayWord = displayWord;
			
			String[] chars = displayWord.split("|");
			int ind = (int) (Math.random() * chars.length);
			int ind2 = realWord.indexOf(chars[ind]);
			if(ind2 == -1) {
				continue;
			}
			PCUtils.<String>swap(chars, ind, ind2);
			
			displayWord = Arrays.stream(chars).collect(Collectors.joining());
			if (!oldDisplayWord.equals(displayWord)) {
				try_++;
			}
			if (displayWord.equals(realWord)) {
				return;
			}
		}
	}

	@Override
	public String toString() {
		return displayWord;
	}

}

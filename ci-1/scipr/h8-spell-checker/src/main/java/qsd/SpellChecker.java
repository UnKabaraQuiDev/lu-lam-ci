package qsd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpellChecker {

	private Dictionnary dictionary;

	public SpellChecker(Dictionnary dictionary) throws IOException {
		this.dictionary = dictionary;

		final File dictFile = new File("DE_Wortliste_UTF8.txt");
		dictionary.load(dictFile);
	}

	public List<Misspelling> analyseText(String text) {
		final List<Misspelling> result = new ArrayList<>();
		final StringBuilder current = new StringBuilder();

		final Pattern pattern = Pattern.compile("[A-Za-zäöüßÄÖÜ]+");
		final Matcher matcher = pattern.matcher(text);

		while (matcher.find()) {
			final String word = matcher.group().toLowerCase();
			final int start = matcher.start();

			if (!dictionary.contains(word)) {
				result.add(new Misspelling(word, start));
			}
		}

		return result;
	}

	public Dictionnary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionnary dictionary) throws IOException {
		this.dictionary = dictionary;

		final File dictFile = new File("DE_Wortliste_UTF8.txt");
		dictionary.load(dictFile);
	}

}
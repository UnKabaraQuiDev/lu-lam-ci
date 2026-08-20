package qsd;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ArrayListDict implements Dictionnary {
	private final ArrayList<String> words;

	ArrayListDict() {
		words = new ArrayList<>();
	}

	@Override
	public void load(File file) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
			br.lines().forEach(line -> words.add(line.trim().toLowerCase()));
		}
	}

	public boolean contains(String word) {
		return words.contains(word);
	}
}
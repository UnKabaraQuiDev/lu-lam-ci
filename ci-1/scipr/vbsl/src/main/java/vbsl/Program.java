package vbsl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InvalidNameException;

public class Program {

	private Command root;
	private Command last;
	final Map<String, Integer> variables = new HashMap<>();
	final Map<String, Command> jumpPoints = new HashMap<>();

	public void add(final Command c) {
		if (root == null) {
			this.root = c;
			this.last = c;
		} else {
			this.last.setNext(c);
			this.last = c;
		}
	}

	public void clear() {
		this.root = null;
		this.last = null;
	}

	public void execute() throws InvalidNameException {
		Command next = this.root;
		while (next != null) {
			next = next.execute(this);
		}
	}

	public String getText() {
		final StringBuilder sb = new StringBuilder();
		Command next = this.root;
		while (next != null) {
			sb.append(next.toString()).append(System.lineSeparator());
			next = next.getNext();
		}
		return sb.toString();
	}

	public void loadFromText(String s) {
		clear();
		final String[] lines = s.split("(\\r\\n|\\r|\\n)");
		for (String l : lines) {
			final Command c = new Command(l);
			add(c);
		}
	}

	public void saveToFile(File f) {
		try {
			Files.write(Paths.get(f.getPath()), getText().getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadFromFile(File f) {
		try {
			loadFromText(new String(Files.readAllBytes(Paths.get(f.getPath()))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

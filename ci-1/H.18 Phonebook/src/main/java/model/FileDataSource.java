package model;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileDataSource implements DataSource {

	public static final String FILEPATH = "saveFile.csv";

	@Override
	public void load(PersonList pl) {
		try (final BufferedReader in = Files.newBufferedReader(Paths.get(FILEPATH), Charset.defaultCharset())) {
			in.lines().forEach(line -> {
				final String[] tokens = line.split(";");
				pl.add(new Person(tokens[0], tokens[1], tokens[2]));
			});
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void save(PersonList pl) {
		try (final PrintWriter out = new PrintWriter(new FileWriter(FILEPATH))) {
			for (Person p : pl) {
				System.err.println(p.toFile());
				out.println(p.toFile());
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	@Deprecated
	public void update(Person p) {
	}

	@Override
	public Person insert(PersonList pl) {
		final Person p = new Person();
		pl.add(p);
		return p;
	}

}

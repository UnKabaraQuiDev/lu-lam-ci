package l3.lexer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class StringUtils {

	public static String readAll(Reader reader) throws IOException {
		try (BufferedReader r = new BufferedReader(reader)) {
			StringBuilder stringBuilder = new StringBuilder();
			String line;

			while ((line = r.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}

			return stringBuilder.toString();
		}
	}

	public static String repeat(String string, int indent) {
		String str = "";
		for (int i = 0; i < indent; i++) {
			str += string;
		}
		return str;
	}

}

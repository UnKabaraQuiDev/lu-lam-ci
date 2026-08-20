package vbsl;

import java.util.Arrays;
import java.util.HashSet;

public class Language {

	private static final String[] knownCommands = { "def", "set", "dec", "inc", "jpd", "jiz", "jnz", "inp", "out" };

	public static final boolean isValid(Command c) {
		return new HashSet<>(Arrays.asList(knownCommands)).contains(c.getCmd());
	}

}

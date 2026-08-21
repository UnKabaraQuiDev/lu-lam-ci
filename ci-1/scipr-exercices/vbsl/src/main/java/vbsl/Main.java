package vbsl;

import javax.naming.InvalidNameException;

public class Main {

	public static void main(String[] args) throws InvalidNameException {
		final Program p = new Program();
		p.loadFromText("""
				// test comment
				def a
				def b
				inp a
				inp b
				jpd next
				dec b
				inc a
				jnz b next
				out result = a
				""");
		p.execute();
	}

}

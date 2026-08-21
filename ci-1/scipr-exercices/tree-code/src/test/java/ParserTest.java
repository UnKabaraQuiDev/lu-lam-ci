import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import lu.pcy113.pclib.PCUtils;

import custom.parser.CustomParser;
import l3.lexer.LexerException;
import l3.parser.ParserException;

public class ParserTest {

	@Test
	public void test() throws IOException, LexerException, ParserException {
		final CustomParser parser = new CustomParser(new StringReader(PCUtils.readPackagedStringFile("/test.tc")));
		System.out.println(parser.getRoot());
	}
	
}

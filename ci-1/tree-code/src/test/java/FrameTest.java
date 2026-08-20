import java.io.IOException;
import java.io.StringReader;

import lu.pcy113.pclib.PCUtils;

import custom.executor.BasicExecutor;
import custom.executor.MainFrame;
import custom.parser.CustomParser;
import l3.lexer.LexerException;
import l3.parser.ParserException;

public class FrameTest {

	public static void main(String[] args) throws IOException, LexerException, ParserException, InterruptedException {
		final CustomParser parser = new CustomParser(new StringReader(PCUtils.readPackagedStringFile("/test.tc")));
		System.out.println(parser.getRoot());
		new MainFrame(new BasicExecutor(parser.getRoot()));
	}

}

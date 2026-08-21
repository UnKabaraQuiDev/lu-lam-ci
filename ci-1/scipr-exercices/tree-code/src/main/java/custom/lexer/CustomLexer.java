package custom.lexer;

import java.io.IOException;
import java.io.Reader;

import l3.lexer.L3Lexer;
import l3.lexer.TokenType;

public class CustomLexer extends L3Lexer {

	public CustomLexer(Reader reader) throws IOException {
		super(reader);
	}

	@Override
	protected TokenType getIdentType(String strValue) {
		if (CustomTokenTypes.ALL.containsKey(strValue)) {
			return CustomTokenTypes.ALL.get(strValue);
		}
		throw new IllegalArgumentException(strValue);
	}
}

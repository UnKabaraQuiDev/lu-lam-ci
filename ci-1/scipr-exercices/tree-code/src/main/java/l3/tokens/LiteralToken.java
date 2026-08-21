package l3.tokens;

import l3.lexer.TokenType;
import l3.lexer.ValueType;
import l3.parser.Token;

public abstract class LiteralToken<T> extends Token {

	public LiteralToken(TokenType type, int line, int column) {
		super(type, line, column);
	}

	public abstract T getValue();

	public abstract ValueType getValueType();

}

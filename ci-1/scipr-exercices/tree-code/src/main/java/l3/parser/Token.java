package l3.parser;

import l3.lexer.TokenType;

public class Token {

	protected int line, column;
	protected TokenType type;

	public Token(TokenType _t, int _l, int _c) {
		this.type = _t;
		this.line = _l + 1;
		this.column = _c + 1;
	}

	public int getColumn() {
		return column;
	}

	public int getLine() {
		return line;
	}

	public TokenType getType() {
		return type;
	}

	public String getPosition() {
		return (line) + ":" + (column);
	}

	@Override
	public String toString() {
		return Token.class.getName() + "[" + getPosition() + ", type=" + type + "]";
	}

	public String toString(int i) {
		return "'" + type.name() + "' at " + getPosition();
	}

}

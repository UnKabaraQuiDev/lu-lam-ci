package l3.tokens;

import l3.lexer.TokenType;
import l3.parser.Token;

public class CommentToken extends Token {

	private String value;

	public CommentToken(TokenType _t, int _l, int _c, String value) {
		super(_t, _l, _c);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return CommentToken.class.getName() + "[line=" + line + ", column=" + column + ", type=" + type + ", value="
				+ value + "]";
	}

}

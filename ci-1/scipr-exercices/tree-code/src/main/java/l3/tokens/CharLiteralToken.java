package l3.tokens;

import l3.lexer.TokenTypes;
import l3.lexer.ValueType;

@Deprecated
public class CharLiteralToken extends LiteralToken<Character> {

	protected Character value;

	public CharLiteralToken(TokenTypes type, int line, int column, Character value) {
		super(type, line, column);
		this.value = value;
	}

	@Override
	public Character getValue() {
		return value;
	}

	@Override
	public ValueType getValueType() {
		return ValueType.CHAR;
	}

	@Override
	public String toString() {
		return StringLiteralToken.class.getName() + "[line=" + line + ", column=" + column + ", type=" + type
				+ ", value=" + value + "]";
	}

}

package l3.tokens;

import l3.lexer.TokenType;
import l3.lexer.ValueType;

public class StringLiteralToken extends LiteralToken<String> {

	protected String value;

	public StringLiteralToken(TokenType type, int line, int column, String value) {
		super(type, line, column);
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public ValueType getValueType() {
		return ValueType.STRING;
	}

	@Override
	public String toString() {
		return StringLiteralToken.class.getName() + "[line=" + line + ", column=" + column + ", type=" + type
				+ ", value=" + value + "]";
	}

	public String getEscapedValue() {
		return getValue().replace("\\", "\\\\");
	}

}

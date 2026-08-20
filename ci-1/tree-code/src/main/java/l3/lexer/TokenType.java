package l3.lexer;

public interface TokenType {

	boolean matches(TokenType type);

	char getCharValue();

	String getStringValue();

	boolean isFixed();

	boolean isString();

	default Object getValue() {
		return !isFixed() ? name() : (isString() ? getStringValue() : getCharValue());
	}

	default String getValueAsString() {
		return !isFixed() ? name() : (isString() ? getStringValue() : Character.toString(getCharValue()));
	}

	String name();

}

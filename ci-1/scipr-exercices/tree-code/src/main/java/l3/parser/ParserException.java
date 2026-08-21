package l3.parser;

import java.util.Arrays;
import java.util.stream.Collectors;

import l3.lexer.TokenType;

public class ParserException extends Exception {

	public ParserException(String string) {
		super(string);
	}

	public ParserException(Token t, TokenType type) {
		super("Expected '" + type.getValue() + "' but got " + t);
	}

	public ParserException(Token t, TokenType... type) {
		super(String.format("Expected '%s' but got " + t,
				Arrays.stream(type).map(a -> a.getValue().toString()).collect(Collectors.joining("', '"))));
	}

	public ParserException(String message, TokenType t) {
		super(String.format(message, t.getValue()));
	}

	public ParserException(String message, TokenType... t) {
		super(String.format(message,
				Arrays.stream(t).map(a -> a.getValue().toString()).collect(Collectors.joining(", "))));
	}

}

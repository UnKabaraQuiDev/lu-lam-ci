package custom;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

import lu.pcy113.pclib.logger.GlobalLogger;
import lu.pcy113.pclib.logger.PCLogger;

import l3.lexer.L3Lexer;
import l3.lexer.LexerException;
import l3.lexer.TokenType;
import l3.lexer.TokenTypes;
import l3.parser.ParserException;
import l3.parser.Token;
import l3.tokens.NumericLiteralToken;

public class Parser {

	public static final String DEBUG_PROPERTY = Parser.class.getSimpleName() + ".debug";
	public static boolean DEBUG = Boolean.getBoolean(DEBUG_PROPERTY);

	private static final PCLogger LOGGER = GlobalLogger.getLogger();

	protected int index = 0;
	protected List<Token> input;

	public Parser(L3Lexer lexer) throws IOException, LexerException {
		lexer.lexe();
		this.input = lexer.getTokens();
	}

	protected Number consumeNumericLiteral() throws ParserException {
		return ((NumericLiteralToken) consume(TokenTypes.NUM_LIT)).getValue();
	}

	protected boolean hasNext() {
		return index < input.size();
	}

	protected boolean hasNext(int x) {
		return index + x < input.size();
	}

	protected Token consume() throws ParserException {
		return consume(0);
	}

	protected Token consume(int i) throws ParserException {
		end();
		Token c = input.get(index);
		index += i + 1; // as for parity with peek(int)
		return c;
	}

	protected Token consume(TokenType t) throws ParserException {
		if (!hasNext()) {
			throw new ParserException(
					"Expected %s but got end of input at: " + peek(-1).getPosition() + "->" + peek().getPosition(), t);
		}

		if (peek(t)) {
			return consume();
		} else {
			throw new ParserException(peek(), t);
		}
	}

	protected Token consume(TokenType... types) throws ParserException {
		final Token peek = peek();
		if (Arrays.stream(types).filter(peek.getType()::matches).collect(Collectors.counting()) > 0) {
			if (DEBUG) {
				LOGGER.log(Level.INFO, "Consuming: " + peek.getType() + " matching " + Arrays.toString(types));
			}
			return consume();
		} else {
			throw new ParserException(peek, types);
		}
	}

	protected void end() throws ParserException {
		if (!hasNext()) {
			throw new ParserException("Unexpected end of input.");
		}
	}

	protected Token peek() {
		return peek(0);
	}

	protected Token peek(int i) {
		return input.get(index + i);
	}

	protected boolean peek(TokenType type) {
		if (!hasNext()) {
			if (DEBUG) {
				LOGGER.log(Level.INFO, "Peek: EOS");
			}
			return false;
		}
		final TokenType peekType = peek().getType();
		if (DEBUG) {
			LOGGER.log(Level.INFO, "Peeking: " + peekType + " matching " + type + " ? " + peekType.matches(type));
		}
		return peekType.matches(type);
	}

	protected boolean peek(int x, TokenType type) {
		if (!hasNext(x)) {
			return false;
		}
		final TokenType peekType = peek(x).getType();
		if (DEBUG) {
			LOGGER.log(Level.INFO, "Peeking: " + peekType + " matching " + type + " ? " + peekType.matches(type));
		}
		return peekType.matches(type);
	}

	protected boolean peek(TokenType... types) {
		if (!hasNext()) {
			return false;
		}
		final TokenType peekType = peek().getType();
		if (Arrays.stream(types).anyMatch(f -> peekType.matches(f))) {
			if (DEBUG) {
				LOGGER.log(Level.INFO, "Peeking: " + peekType + " matching " + Arrays.toString(types));
			}
			return true;
		}
		return false;
	}

	protected boolean peek(int x, TokenType... types) {
		final TokenType peekType = peek(x).getType();
		if (Arrays.stream(types).anyMatch(f -> peekType.matches(f))) {
			if (DEBUG) {
				LOGGER.log(Level.INFO, "Peeking: " + peekType + " matching " + Arrays.toString(types));
			}
			return true;
		}
		return false;
	}

}

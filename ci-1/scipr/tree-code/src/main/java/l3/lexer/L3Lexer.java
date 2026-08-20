package l3.lexer;

import static l3.lexer.TokenTypes.AND;
import static l3.lexer.TokenTypes.ARROW;
import static l3.lexer.TokenTypes.AS;
import static l3.lexer.TokenTypes.BIN_NUM_LIT;
import static l3.lexer.TokenTypes.BIT_AND;
import static l3.lexer.TokenTypes.BIT_AND_ASSIGN;
import static l3.lexer.TokenTypes.BIT_NOT;
import static l3.lexer.TokenTypes.BIT_NOT_ASSIGN;
import static l3.lexer.TokenTypes.BIT_OR;
import static l3.lexer.TokenTypes.BIT_OR_ASSIGN;
import static l3.lexer.TokenTypes.BIT_XOR;
import static l3.lexer.TokenTypes.BIT_XOR_ASSIGN;
import static l3.lexer.TokenTypes.BRACKET_CLOSE;
import static l3.lexer.TokenTypes.BRACKET_OPEN;
import static l3.lexer.TokenTypes.BYTE;
import static l3.lexer.TokenTypes.CASE;
import static l3.lexer.TokenTypes.CHAR;
import static l3.lexer.TokenTypes.CHAR_LIT;
import static l3.lexer.TokenTypes.COLON;
import static l3.lexer.TokenTypes.COMMA;
import static l3.lexer.TokenTypes.COMMENT;
import static l3.lexer.TokenTypes.CURLY_CLOSE;
import static l3.lexer.TokenTypes.CURLY_OPEN;
import static l3.lexer.TokenTypes.DEC_NUM_LIT;
import static l3.lexer.TokenTypes.DEFAULT;
import static l3.lexer.TokenTypes.DIV;
import static l3.lexer.TokenTypes.DIV_ASSIGN;
import static l3.lexer.TokenTypes.DOLLAR;
import static l3.lexer.TokenTypes.DOT;
import static l3.lexer.TokenTypes.DOUBLE;
import static l3.lexer.TokenTypes.ELSE;
import static l3.lexer.TokenTypes.EQUALS;
import static l3.lexer.TokenTypes.FALSE;
import static l3.lexer.TokenTypes.FINALLY;
import static l3.lexer.TokenTypes.FLOAT;
import static l3.lexer.TokenTypes.FOR;
import static l3.lexer.TokenTypes.FUN;
import static l3.lexer.TokenTypes.GOTO;
import static l3.lexer.TokenTypes.GREATER;
import static l3.lexer.TokenTypes.GREATER_EQUALS;
import static l3.lexer.TokenTypes.HASH;
import static l3.lexer.TokenTypes.HEX_NUM_LIT;
import static l3.lexer.TokenTypes.IDENT;
import static l3.lexer.TokenTypes.IF;
import static l3.lexer.TokenTypes.IMPORT;
import static l3.lexer.TokenTypes.INT;
import static l3.lexer.TokenTypes.INT_1;
import static l3.lexer.TokenTypes.INT_16;
import static l3.lexer.TokenTypes.INT_32;
import static l3.lexer.TokenTypes.INT_64;
import static l3.lexer.TokenTypes.INT_8;
import static l3.lexer.TokenTypes.LESS;
import static l3.lexer.TokenTypes.LESS_EQUALS;
import static l3.lexer.TokenTypes.LET;
import static l3.lexer.TokenTypes.LONG;
import static l3.lexer.TokenTypes.MINUS;
import static l3.lexer.TokenTypes.MINUS_ASSIGN;
import static l3.lexer.TokenTypes.MINUS_MINUS;
import static l3.lexer.TokenTypes.MODULO;
import static l3.lexer.TokenTypes.MODULO_ASSIGN;
import static l3.lexer.TokenTypes.MUL;
import static l3.lexer.TokenTypes.MUL_ASSIGN;
import static l3.lexer.TokenTypes.NEW;
import static l3.lexer.TokenTypes.NOT;
import static l3.lexer.TokenTypes.NOT_EQUALS;
import static l3.lexer.TokenTypes.NUM_LIT;
import static l3.lexer.TokenTypes.OR;
import static l3.lexer.TokenTypes.PACKAGE;
import static l3.lexer.TokenTypes.PAREN_CLOSE;
import static l3.lexer.TokenTypes.PAREN_OPEN;
import static l3.lexer.TokenTypes.PLUS;
import static l3.lexer.TokenTypes.PLUS_ASSIGN;
import static l3.lexer.TokenTypes.PLUS_PLUS;
import static l3.lexer.TokenTypes.RETURN;
import static l3.lexer.TokenTypes.SEMICOLON;
import static l3.lexer.TokenTypes.SHORT;
import static l3.lexer.TokenTypes.STATIC;
import static l3.lexer.TokenTypes.STRICT_ASSIGN;
import static l3.lexer.TokenTypes.STRING_LIT;
import static l3.lexer.TokenTypes.STRUCT;
import static l3.lexer.TokenTypes.SWITCH;
import static l3.lexer.TokenTypes.TRUE;
import static l3.lexer.TokenTypes.TYPE;
import static l3.lexer.TokenTypes.VOID;
import static l3.lexer.TokenTypes.WHILE;
import static l3.lexer.TokenTypes.XOR;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import l3.parser.Token;
import l3.tokens.CommentToken;
import l3.tokens.IdentifierToken;
import l3.tokens.NumericLiteralToken;
import l3.tokens.StringLiteralToken;

public class L3Lexer {

	private int index = 0, line = 0, column = 0;
	private final String input;
	private final List<Token> tokens;

	public L3Lexer(Reader reader) throws IOException {
		this.input = StringUtils.readAll(reader);
		this.tokens = new ArrayList<Token>();
	}

	private TokenType type = null;
	private String strValue = "";

	public void lexe() throws LexerException {
		while (hasNext()) {
			next: {
				char current = consume();

				switch (current) {
				case '+':
					if (peek() == '=') {
						consume();
						type = PLUS_ASSIGN;
					} else if (peek() == '+') {
						consume();
						type = PLUS_PLUS;
					} else {
						type = PLUS;
					}
					flushToken();
					break next;
				case '-':
					if (peek() == '>') {
						consume();
						type = ARROW;
					} else if (peek() == '=') {
						consume();
						type = MINUS_ASSIGN;
					} else if (peek() == '-') {
						consume();
						type = MINUS_MINUS;
					} else {
						type = MINUS;
					}
					flushToken();
					break next;
				case '*':
					if (peek() == '=') {
						consume();
						type = MUL_ASSIGN;
					} else {
						type = MUL;
					}
					flushToken();
					break next;
				case '/':
					if (peek() == '/') {
						consume();
						type = COMMENT;
						strValue = "//";
						while (hasNext() && peek() != '\n') { // ignore ligne
							strValue += consume();
						}
						flushToken();
						break next;
					} else if (peek() == '*') {
						consume();
						type = COMMENT;
						strValue = "/*";
						while (hasNext() && peek() != '*' && peek(1) != '/') {
							strValue += consume();
						}
						consume(2);
						strValue += "*/";
						flushToken();
						break next;
					} else if (peek() == '=') {
						consume();
						type = DIV_ASSIGN;
					} else {
						type = DIV;
					}
					flushToken();
					break next;

				case '(':
					type = PAREN_OPEN;
					flushToken();
					break next;
				case ')':
					type = PAREN_CLOSE;
					flushToken();
					break next;
				case '[':
					type = BRACKET_OPEN;
					flushToken();
					break next;
				case ']':
					type = BRACKET_CLOSE;
					flushToken();
					break next;
				case '{':
					type = CURLY_OPEN;
					flushToken();
					break next;
				case '}':
					type = CURLY_CLOSE;
					flushToken();
					break next;

				case '\"':
					type = STRING_LIT;
					strValue = "";
					int cl = line, cc = column;
					while (hasNext() && peek() != '\"') {
						if (peek("\\")) {
							consume();
							if (peek('0', 'e', 'f', 'v', 'b', 't', 'n', 'r')) {
								String strV = ("\\" + consume()).replace("\\n", "\n").replace("\\r", "\r")
										.replace("\\t", "\t").replace("\\b", "\b")
										// .replace("\\v", "\v")
										.replace("\\f", "\f")
										// .replace("\\e", "\e")
										.replace("\\0", "\0");
								strValue += strV;
							}
						} else {
							strValue += consume();
						}
					}
					if (!hasNext()) {
						throw new LexerException("Unterminated string, starting at: " + cl + ":" + cc);
					}
					consume();
					flushToken();
					break next;

				case '\'':
					type = CHAR_LIT;
					cl = line;
					cc = column;
					strValue = consume() + "";
					if (!peek("'") || !hasNext()) {
						throw new LexerException("Unterminated string, starting at: " + cl + ":" + cc);
					}
					consume();
					flushToken();
					break next;

				case '$':
					type = DOLLAR;
					flushToken();
					break next;

				case ':':
					type = COLON;
					flushToken();
					break next;

				case ';':
					type = SEMICOLON;
					flushToken();
					break next;

				case ',':
					type = COMMA;
					flushToken();
					break next;

				case '.':
					type = DOT;
					flushToken();
					break next;

				case 'i':
					strValue = "i";
					if (peek("nt")) {
						consume(2);
						strValue += "nt";
					}
					if (peek("8")) {
						consume(1);
						type = INT_8;
						strValue += "8";
					} else if (peek("16")) {
						consume(2);
						type = INT_16;
						strValue += "16";
					} else if (peek("32")) {
						consume(2);
						type = INT_32;
						strValue += "32";
					} else if (peek("64")) {
						consume(2);
						type = INT_64;
						strValue += "64";
					} else if (peek("1")) {
						consume(2);
						type = INT_1;
						strValue += "1";
					} else if (strValue.equals("int")) {
						type = INT;
					} else {
						checkOthers(current);
						break next;
					}
					if (type != null && type.matches(TYPE) && peek("s")) {
						consume();
						try {
							type = TokenTypes.valueOf(type.name() + "_S");
						} catch (IllegalArgumentException e) {
							throw new LexerException(e, "Unknown variable type: " + strValue, line, column);
						}
					}
					if (type != null) {
						flushToken();
						break next;
					}
					break;

				case '|':
					if (peek() == '|') {
						consume();
						type = OR;
					} else if (peek() == '=') {
						consume();
						type = BIT_OR_ASSIGN;
					} else {
						type = BIT_OR;
					}
					flushToken();
					break next;

				case '&':
					if (peek() == '&') {
						consume();
						type = AND;
					} else if (peek() == '=') {
						consume();
						type = BIT_AND_ASSIGN;
					} else {
						type = BIT_AND;
					}
					flushToken();
					break next;

				case '%':
					if (peek() == '=') {
						consume();
						type = MODULO_ASSIGN;
					} else {
						type = MODULO;
					}
					flushToken();
					break next;

				case '#':
					type = HASH;
					flushToken();
					break next;

				case '!':
					if (peek() == '=') {
						consume();
						type = NOT_EQUALS;
					} else {
						type = NOT;
					}
					flushToken();
					break next;

				case '^':
					if (peek() == '^') {
						consume();
						type = XOR;
					} else if (peek() == '=') {
						consume();
						type = BIT_XOR_ASSIGN;
					} else {
						type = BIT_XOR;
					}
					flushToken();
					break next;

				case '~':
					if (peek() == '=') {
						consume();
						type = BIT_NOT_ASSIGN;
					} else {
						type = BIT_NOT;
					}
					flushToken();
					break next;

				case '=':
					if (peek() == '=') {
						consume();
						type = EQUALS;
					} else {
						type = STRICT_ASSIGN;
					}
					flushToken();
					break next;
				case '<':
					if (peek() == '=') {
						consume();
						type = LESS_EQUALS;
					} else {
						type = LESS;
					}
					flushToken();
					break next;
				case '>':
					if (peek() == '=') {
						consume();
						type = GREATER_EQUALS;
					} else {
						type = GREATER;
					}
					flushToken();
					break next;

				case ' ':
				case '\t':
				case '\n':
				case '\r':
					if (IDENT.equals(type) || NUM_LIT.equals(type) || DEC_NUM_LIT.equals(type)) {
						flushToken();
						break next;
					}
					break;

				case '0':
					if (peek() == 'x') {
						consume();
						type = HEX_NUM_LIT;
						do {
							strValue += consume();
						} while (Character.isLetterOrDigit(peek()) || peek() == '_');
						flushToken();
						break next;
					} else if (peek() == 'b') {
						consume();
						type = BIN_NUM_LIT;
						do {
							strValue += consume();
						} while (peek() == '1' || peek() == '0' || peek() == '_');
						flushToken();
						break next;
					} else if (peek() == 'o') {
						consume();
						type = BIN_NUM_LIT;
						do {
							strValue += consume();
						} while (isOctalDigit((char) peek()) || peek() == '_');
						flushToken();
						break next;
					}
				}

				checkOthers(current);
			}
		}
		// flushToken();
	}

	private void checkOthers(char current) throws LexerException {
		if (type == null && Character.isLetter(current)) {
			type = IDENT;
			strValue = Character.toString(current);
			while (Character.isLetterOrDigit(peek()) || peek() == '_') {
				strValue += consume();
			}

			switch (strValue.toLowerCase()) {
			case "if" -> type = IF;
			case "else" -> type = ELSE;
			case "finally" -> type = FINALLY;
			case "for" -> type = FOR;
			case "while" -> type = WHILE;
			case "switch" -> type = SWITCH;
			case "case" -> type = CASE;
			case "default" -> type = DEFAULT;
			case "void" -> type = VOID;
			case "true" -> type = TRUE;
			case "false" -> type = FALSE;
			case "new" -> type = NEW;
			case "let" -> type = LET;
			case "fun" -> type = FUN;
			case "static" -> type = STATIC;
			case "return" -> type = RETURN;
			case "package" -> type = PACKAGE;
			case "import" -> type = IMPORT;
			case "as" -> type = AS;
			case "struct" -> type = STRUCT;
			case "byte" -> type = BYTE;
			case "short" -> type = SHORT;
			case "char" -> type = CHAR;
			case "long" -> type = LONG;
			case "float" -> type = FLOAT;
			case "double" -> type = DOUBLE;
			case "goto" -> type = GOTO;
			default -> type = getIdentType(strValue);
			}

			flushToken();
		} else if (type == null && Character.isDigit(current)) {
			type = NUM_LIT;
			strValue = "" + current;
			while (Character.isLetterOrDigit(peek()) || peek() == '_' || peek() == '.' || peek() == 'f') {
				strValue += consume();
			}
			if (strValue.contains(".") || strValue.contains("f")) {
				type = DEC_NUM_LIT;
			}
			flushToken();
		}
	}

	/**
	 * to be overriden
	 */
	protected TokenType getIdentType(String strValue) {
		throw new UnsupportedOperationException(strValue);
	}

	protected void flushToken() throws LexerException {
		if (type == null)
			return;

		if (IDENT.equals(type)) {
			tokens.add(new IdentifierToken(type, line, column - strValue.length(), strValue));
		} else if (NUM_LIT.equals(type) || CHAR_LIT.equals(type) || DEC_NUM_LIT.equals(type) || HEX_NUM_LIT.equals(type)
				|| BIN_NUM_LIT.equals(type)) {
			tokens.add(new NumericLiteralToken(type, line, column - strValue.length(), strValue));
		} else if (STRING_LIT.equals(type)) {
			tokens.add(new StringLiteralToken(type, line, column - strValue.length(), strValue));
		} else if (COMMENT.equals(type)) {
			tokens.add(new CommentToken(type, line, column - strValue.length(), strValue));
		} else {
			tokens.add(new Token(type, line, column - strValue.length()));
		}

		type = null;
		strValue = "";
	}

	protected boolean hasNext() {
		return index < input.length();
	}

	protected boolean hasNext(int i) {
		return index + 1 < input.length();
	}

	protected char consume() {
		return consume(1);
	}

	protected char consume(int i) {
		char c = input.charAt(index);
		index += i;
		column++;
		if (c == '\n') {
			line++;
			column = 0;
		}
		return c;
	}

	protected int peek() {
		return peek(0);
	}

	protected void reverse() {
		index--;
	}

	protected boolean peek(String s) {
		boolean b = true;
		for (int i = 0; i < s.length(); i++) {
			if (peek(i) == s.charAt(i))
				continue;
			b = false;
			break;
		}
		return b;
	}

	protected boolean peek(char... s) {
		int c = peek();
		for (char cs : s) {
			if (cs == c)
				return true;
		}
		return false;
	}

	protected boolean peek(int x, char... s) {
		int c = peek(x);
		for (char cs : s) {
			if (cs == c)
				return true;
		}
		return false;
	}

	protected boolean peek(int x, String s) {
		boolean b = true;
		for (int i = 0; i < s.length(); i++) {
			if (peek(i + x) == s.charAt(i)) {
				continue;
			}
			b = false;
			break;
		}
		return b;
	}

	protected int peek(int i) {
		return input.charAt(index + i);
	}

	public String getInput() {
		return input;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public static boolean isOctalDigit(char digit) {
		if (Character.isDigit(digit)) {
			int numericValue = Character.getNumericValue(digit);
			return numericValue >= 0 && numericValue <= 7;
		}
		return false;
	}

}

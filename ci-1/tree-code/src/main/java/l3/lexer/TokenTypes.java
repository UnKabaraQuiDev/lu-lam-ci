package l3.lexer;

public enum TokenTypes implements TokenType {

	LET("let"), FUN("fun"),

	RETURN("return"),

	IMPORT("import"), PACKAGE("package"), AS("as"),

	CLASS("class"), INTERFACE("interface"), STRUCT("struct"), UNION("union"),

	TYPE(), USER_TYPE(TYPE), PRIMITIVE_TYPE(TYPE),

	INT(PRIMITIVE_TYPE, "int"),

	INT_1(INT, "int1"), INT_8(INT, "int8"), INT_16(INT, "int16"), INT_32(INT, "int32"), INT_64(INT, "int64"),
	INT_128(INT, "int128"),

	INT_8_S(INT, "int8s"), INT_16_S(INT, "int16s"), INT_32_S(INT, "int32s"), INT_64_S(INT, "int64s"),
	INT_128_S(INT, "int128s"),

	BYTE(INT_8, "byte"), CHAR(BYTE, "char"), SHORT(INT_16, "short"), LONG(INT_64, "long"),

	FLOAT(PRIMITIVE_TYPE, "float"),

	FLOAT_32(FLOAT, "float32"), /* FLOAT_128(FLOAT, "float128"), */

	DOUBLE(PRIMITIVE_TYPE, "double"),

	BOOLEAN(PRIMITIVE_TYPE),

	TRUE("true"), FALSE("false"),

	VOID("void"), NEW("new"),

	NUM_LIT(), DEC_NUM_LIT(NUM_LIT), HEX_NUM_LIT(NUM_LIT), BIN_NUM_LIT(NUM_LIT), CHAR_LIT(NUM_LIT),

	IDENT(),

	COMMA(','), DOT('.'), COLON(':'), SEMICOLON(';'), DOLLAR('$'),

	ARROW("->"),

	PAREN_OPEN('('), PAREN_CLOSE(')'), BRACKET_OPEN('['), BRACKET_CLOSE(']'), CURLY_OPEN('{'), CURLY_CLOSE('}'),

	STRING_LIT(),

	COMMENT("//"),

	IF("if"), ELSE("else"),

	STATIC("static"),

	SWITCH("switch"), CASE("case"), DEFAULT("default"),

	FOR("for"), WHILE("while"), BREAK("break"),

	DO("do"), FINALLY("finally"),

	GOTO("goto"), YIELD("yield"),

	ASSIGN(), STRICT_ASSIGN(ASSIGN, '='),

	BIT_OR('|'), BIT_AND('&'), BIT_XOR('^'), BIT_NOT('~'),

	BIT_OR_ASSIGN(ASSIGN, "|="), BIT_AND_ASSIGN(ASSIGN, "&="), BIT_XOR_ASSIGN(ASSIGN, "^="),
	BIT_NOT_ASSIGN(ASSIGN, "~="),

	OR("||"), AND("&&"), NOT('!'), XOR("^^"),

	HASH('#'),

	PLUS('+'), MINUS('-'), MUL('*'), DIV('/'), MODULO('%'),

	PLUS_PLUS("++"), MINUS_MINUS("--"),

	PLUS_ASSIGN(ASSIGN, "+="), MINUS_ASSIGN(ASSIGN, "-="), MUL_ASSIGN(ASSIGN, "*="), DIV_ASSIGN(ASSIGN, "/="),
	MODULO_ASSIGN(ASSIGN, "%="),

	EQUALS("=="), NOT_EQUALS("!="),

	LESS('<'), LESS_EQUALS("<="),

	GREATER('>'), GREATER_EQUALS(">=");

	// CUSTOM
	// MOVE("move"), UP("up"), DOWN("down"), ROTATE("rotate");

	private TokenTypes parent;
	private boolean fixed = false;
	private boolean string = false;
	private String stringValue;
	private Character charValue;

	private TokenTypes() {
		this.fixed = false;
	}

	private TokenTypes(TokenTypes parent) {
		this.fixed = false;
		this.parent = parent;
	}

	private TokenTypes(char cha) {
		this.fixed = true;
		this.string = false;
		this.charValue = cha;
	}

	private TokenTypes(TokenTypes parent, char cha) {
		this.fixed = true;
		this.string = false;
		this.charValue = cha;
		this.parent = parent;
	}

	private TokenTypes(String str) {
		this.fixed = true;
		this.string = true;
		this.stringValue = str;
	}

	private TokenTypes(TokenTypes parent, String str) {
		this.fixed = true;
		this.string = true;
		this.stringValue = str;
		this.parent = parent;
	}

	@Override
	public boolean matches(TokenType type) {
		return this.equals(type) || (parent != null ? parent.matches(type) : false);
	}

	public boolean isFixed() {
		return fixed;
	}

	public boolean isString() {
		return string;
	}

	@Override
	public String getStringValue() {
		return stringValue;
	}

	@Override
	public char getCharValue() {
		return charValue;
	}

	@Override
	public String toString() {
		if (fixed && string) {
			return TokenTypes.class.getSimpleName() + "[" + name() + ", fixed=" + fixed + ", string=" + string
					+ ", stringValue=" + stringValue + "]";
		} else if (fixed && !string) {
			return TokenTypes.class.getSimpleName() + "[" + name() + ", fixed=" + fixed + ", string=" + string
					+ ", charValue=" + charValue + "]";
		} else {
			return TokenTypes.class.getSimpleName() + "[" + name() + ", fixed=" + fixed + ", string=" + string + "]";
		}
	}

	public String toShortString() {
		return TokenTypes.class.getSimpleName() + "[" + name() + "]";
	}

}

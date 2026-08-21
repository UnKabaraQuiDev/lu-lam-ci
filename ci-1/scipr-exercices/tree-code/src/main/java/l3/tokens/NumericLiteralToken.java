package l3.tokens;

import lu.pcy113.pclib.PCUtils;

import l3.formats.BinFormat;
import l3.formats.HexFormat;
import l3.lexer.LexerException;
import l3.lexer.TokenType;
import l3.lexer.TokenTypes;
import l3.lexer.ValueType;

public class NumericLiteralToken extends LiteralToken<Number> {

	protected String literal;
	protected Number value;
	protected ValueType valueType;

	public NumericLiteralToken(TokenType type, int line, int column, String literal) throws LexerException {
		super(type, line, column);

		boolean float_ = literal.endsWith("f");

		literal = PCUtils.replaceLast(literal.trim().replace("_", ""), "f", "");

		this.literal = literal;
		if (float_) {
			try {
				value = Float.parseFloat(literal);
				valueType = ValueType.FLOAT;
			} catch (NumberFormatException e) {
				throw new LexerException(e, "Invalid number format: " + e.getMessage(), line, column, literal);
			}
		} else if (type.equals(TokenTypes.DEC_NUM_LIT)) {
			try {
				value = Double.parseDouble(literal);
				valueType = ValueType.DOUBLE;
			} catch (NumberFormatException e) {
				throw new LexerException(e, "Invalid number format: " + e.getMessage(), line, column, literal);
			}
		} else if (type.equals(TokenTypes.HEX_NUM_LIT)) {
			try {
				value = HexFormat.fromHexDigitsToLong(literal);
			} catch (NumberFormatException e) {
				throw new LexerException(e, "Invalid number format: " + e.getMessage(), line, column, literal);
			}
		} else if (type.equals(TokenTypes.BIN_NUM_LIT)) {
			try {
				value = BinFormat.fromBinDigitsToLong(literal);
			} catch (NumberFormatException e) {
				throw new LexerException(e, "Invalid number format: " + e.getMessage(), line, column, literal);
			}
		} else if (type.equals(TokenTypes.CHAR_LIT)) {
			try {
				value = (long) (char) literal.charAt(0);
			} catch (NumberFormatException e) {
				throw new LexerException(e, "Invalid number format: " + e.getMessage(), line, column, literal);
			}
		} else if (type.equals(TokenTypes.NUM_LIT)) {
			try {
				value = Long.parseLong(literal);
			} catch (NumberFormatException e) {
				throw new LexerException(e, "Invalid number format: " + e.getMessage(), line, column, literal);
			}
		}

		if (value instanceof Long) {
			if ((long) value <= Byte.MAX_VALUE) {
				valueType = ValueType.INT_8;
			} else if ((long) value <= Short.MAX_VALUE) {
				valueType = ValueType.INT_16;
			} else if ((long) value <= Integer.MAX_VALUE) {
				valueType = ValueType.INT_32;
			} else if ((long) value <= Long.MAX_VALUE) {
				valueType = ValueType.INT_64;
			}
		}
	}

	public String getLiteral() {
		return literal;
	}

	public boolean isDouble() {
		return valueType.equals(ValueType.DOUBLE);
	}

	public boolean isFloat() {
		return valueType.equals(ValueType.FLOAT);
	}

	public boolean isInteger() {
		return !isDouble() && !isFloat();
	}

	@Override
	public Number getValue() {
		return value;
	}

	@Override
	public ValueType getValueType() {
		return valueType;
	}

	@Override
	public String toString() {
		return NumericLiteralToken.class.getName() + "[line=" + line + ", column=" + column + ", type=" + type
				+ ", literal=" + literal + ", value=" + value + "]";
	}

}

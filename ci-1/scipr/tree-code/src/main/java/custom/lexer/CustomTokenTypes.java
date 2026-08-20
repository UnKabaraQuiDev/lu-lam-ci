package custom.lexer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import l3.lexer.TokenType;

public enum CustomTokenTypes implements TokenType {

	MOVE("move"), UP("up"), DOWN("down"), ROTATE("rotate"), LOOP("loop"), COLOR("color"), THICKNESS("thickness");

	public static final Map<String, CustomTokenTypes> ALL;
	static {
		final Map<String, CustomTokenTypes> all = new HashMap<>();
		for (CustomTokenTypes ctt : values()) {
			if (ctt.fixed) {
				all.put(ctt.getValueAsString(), ctt);
			}
		}
		ALL = Collections.unmodifiableMap(all);
	}

	private CustomTokenTypes parent;
	private boolean fixed = false;
	private boolean string = false;
	private String stringValue;
	private Character charValue;

	private CustomTokenTypes() {
		this.fixed = false;
	}

	private CustomTokenTypes(CustomTokenTypes parent) {
		this.fixed = false;
		this.parent = parent;
	}

	private CustomTokenTypes(char cha) {
		this.fixed = true;
		this.string = false;
		this.charValue = cha;
	}

	private CustomTokenTypes(CustomTokenTypes parent, char cha) {
		this.fixed = true;
		this.string = false;
		this.charValue = cha;
		this.parent = parent;
	}

	private CustomTokenTypes(String str) {
		this.fixed = true;
		this.string = true;
		this.stringValue = str;
	}

	private CustomTokenTypes(CustomTokenTypes parent, String str) {
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
			return CustomTokenTypes.class.getSimpleName() + "[" + name() + ", fixed=" + fixed + ", string=" + string + ", stringValue="
					+ stringValue + "]";
		} else if (fixed && !string) {
			return CustomTokenTypes.class.getSimpleName() + "[" + name() + ", fixed=" + fixed + ", string=" + string + ", charValue="
					+ charValue + "]";
		} else {
			return CustomTokenTypes.class.getSimpleName() + "[" + name() + ", fixed=" + fixed + ", string=" + string + "]";
		}
	}

	public String toShortString() {
		return CustomTokenTypes.class.getSimpleName() + "[" + name() + "]";
	}

}

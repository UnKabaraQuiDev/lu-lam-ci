package l3.formats;

public final class HexFormat {

	public static Long fromHexDigitsToLong(String str) {
		// Check if the input string is not null or empty
		if (str == null || str.isEmpty()) {
			throw new IllegalArgumentException("Input string cannot be null or empty");
		}

		// Remove any leading "0x" if present
		if (str.startsWith("0x") || str.startsWith("0X")) {
			str = str.substring(2);
		}

		// Convert the hex string to a long
		try {
			return Long.valueOf(str, 16);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid hex string: " + str, e);
		}
	}

}
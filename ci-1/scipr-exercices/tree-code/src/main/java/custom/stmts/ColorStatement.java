package custom.stmts;

import java.awt.Color;

import lu.pcy113.pclib.PCUtils;

public class ColorStatement extends Statement {

	private Color color;

	public ColorStatement(String color) {
		if (color.startsWith("#") && (color.length() == 7 || color.length() == 9)) {
			this.color = PCUtils.hexToColor(color);
		} else {
			throw new UnsupportedOperationException(color);
		}
	}

	public ColorStatement(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "ColorStatement [color=" + color + "]";
	}

}

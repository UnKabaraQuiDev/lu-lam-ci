package custom.parser;

import custom.stmts.Statement;

public class GotoStatement extends Statement {

	private int x, y;

	public GotoStatement(Number numX, Number numY) {
		x = switch (numX) {
		case Long l -> (int) (long) l;
		case Integer l -> (int) l;
		default -> throw new UnsupportedOperationException(numX + " " + numX.getClass());
		};

		y = switch (numY) {
		case Long l -> (int) (long) l;
		case Integer l -> (int) l;
		default -> throw new UnsupportedOperationException(numY + " " + numY.getClass());
		};
	}

	public GotoStatement(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "GotoStatement [x=" + x + ", y=" + y + "]";
	}

}

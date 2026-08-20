package custom.stmts;

public class PenStateStatement extends Statement {

	private boolean down;

	public PenStateStatement(boolean down) {
		this.down = down;
	}

	public boolean isDown() {
		return down;
	}

	@Override
	public String toString() {
		return "PenStateStatement [down=" + down + "]";
	}

}

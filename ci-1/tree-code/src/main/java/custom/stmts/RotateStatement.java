package custom.stmts;

public class RotateStatement extends Statement {

	private int angle;

	public RotateStatement(Number num) {
		if (num instanceof Long l) {
			this.angle = (int) (long) l;
		} else if (num instanceof Integer i) {
			this.angle = (int) i;
		} else {
			throw new IllegalArgumentException(num + " " + num.getClass());
		}
	}

	public RotateStatement(int dist) {
		this.angle = dist;
	}

	public int getAngle() {
		return angle;
	}

	@Override
	public String toString() {
		return "RotateStatement [angle=" + angle + "]";
	}

}

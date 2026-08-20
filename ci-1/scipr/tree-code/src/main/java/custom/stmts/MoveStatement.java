package custom.stmts;

public class MoveStatement extends Statement {

	private int dist;

	public MoveStatement(Number num) {
		if (num instanceof Long l) {
			this.dist = (int) (long) l;
		} else if (num instanceof Integer i) {
			this.dist = (int) i;
		} else {
			throw new IllegalArgumentException(num + " " + num.getClass());
		}
	}

	public MoveStatement(int dist) {
		this.dist = dist;
	}

	public int getDistance() {
		return dist;
	}

	@Override
	public String toString() {
		return "MoveStatement [dist=" + dist + "]";
	}

}

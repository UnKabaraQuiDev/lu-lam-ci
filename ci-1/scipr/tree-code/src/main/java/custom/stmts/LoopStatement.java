package custom.stmts;

public class LoopStatement extends ParentStatement {

	private int count;

	public LoopStatement(Number count) {
		if (count instanceof Long l) {
			this.count = (int) (long) l;
		} else if (count instanceof Integer i) {
			this.count = (int) i;
		} else {
			throw new IllegalArgumentException(count + " " + count.getClass());
		}
	}

	public int getCount() {
		return count;
	}

}

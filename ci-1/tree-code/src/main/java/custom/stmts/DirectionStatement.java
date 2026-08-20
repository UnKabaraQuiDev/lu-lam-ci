package custom.stmts;

import custom.Direction;

@Deprecated
public class DirectionStatement extends Statement {

	private Direction dir;

	public DirectionStatement(Direction dist) {
		this.dir = dist;
	}

}

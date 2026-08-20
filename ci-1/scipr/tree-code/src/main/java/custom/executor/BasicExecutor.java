package custom.executor;

import java.util.function.Consumer;

import custom.parser.GotoStatement;
import custom.stmts.ColorStatement;
import custom.stmts.LoopStatement;
import custom.stmts.MoveStatement;
import custom.stmts.ParentStatement;
import custom.stmts.PenStateStatement;
import custom.stmts.RotateStatement;
import custom.stmts.Statement;
import custom.stmts.ThicknessStatement;

public class BasicExecutor implements Consumer<Pen> {

	private ParentStatement root;

	public BasicExecutor(ParentStatement root) {
		this.root = root;
	}

	@Override
	public void accept(Pen pen) {
		consume(root, pen);
	}

	private void consume(ParentStatement parent, Pen pen) {
		for (Statement stmt : parent) {
			switch (stmt) {
			case ThicknessStatement t -> pen.thickness(t.getThickness());
			case ColorStatement t -> pen.color(t.getColor());

			case MoveStatement t -> pen.move(t.getDistance());
			case RotateStatement t -> pen.rotate(t.getAngle());
			case GotoStatement t -> pen.goto_(t.getX(), t.getY());

			case PenStateStatement t -> pen.state(t.isDown());
			case LoopStatement t -> {
				for (int i = 0; i < t.getCount(); i++)
					consume(t, pen);
			}

			default -> throw new UnsupportedOperationException(stmt + " " + stmt.getClass());
			}
		}
	}

}

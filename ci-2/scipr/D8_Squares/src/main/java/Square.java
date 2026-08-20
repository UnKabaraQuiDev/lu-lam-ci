import java.awt.geom.Rectangle2D;

public class Square extends Rectangle2D.Double {

	public Square(int x, int y, int side) {
		super.x = x;
		super.y = y;
		super.width = super.height = side;
		//super.width = super.height = PCUtils.randomIntRange(10, 50);
	}

}

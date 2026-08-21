import java.awt.Graphics2D;
import java.awt.Point;

public abstract class CustomShape {

	public abstract void setStartpoint(Point p1);

	public abstract void setEndpoint(Point p2);

	public abstract void draw(Graphics2D g2d);

}

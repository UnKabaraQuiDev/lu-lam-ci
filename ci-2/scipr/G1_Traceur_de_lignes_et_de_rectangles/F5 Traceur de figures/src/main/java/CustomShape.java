import java.awt.Graphics2D;
import java.awt.Point;

public interface CustomShape {

	void setStartpoint(Point p1);

	void setEndpoint(Point p2);

	void draw(Graphics2D g2d);

}

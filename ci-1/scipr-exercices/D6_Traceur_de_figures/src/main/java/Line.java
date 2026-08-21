import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

public class Line extends Line2D.Double implements CustomShape {

	private Color color;

	public Line() {
	}

	public Line(Color color2, Point p1, Point p2) {
		super(p1.x, p1.y, p2.x, p2.y);
		this.color = color2;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}

	@Override
	public void setStartpoint(Point p1) {
		super.setLine(p1.getX(), p1.getY(), x2, y2);
	}

	@Override
	public void setEndpoint(Point p2) {
		super.setLine(x1, y1, p2.getX(), p2.getY());
	}

	@Override
	public Point getStartpoint() {
		return new Point((int) x1, (int) y1);
	}

	@Override
	public Point getEndpoint() {
		return new Point((int) x2, (int) y2);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Line [color=" + color + "]";
	}
}

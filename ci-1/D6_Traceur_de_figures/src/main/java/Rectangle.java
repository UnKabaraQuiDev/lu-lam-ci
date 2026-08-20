import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Rectangle2D.Double implements CustomShape {

	private Color color;

	public Rectangle() {
	}

	public Rectangle(Color color2, Point p1, Point p2) {
		super(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.max(p1.x, p2.x) - Math.min(p1.x, p2.x),
				Math.max(p1.y, p2.y) - Math.min(p1.y, p2.y));
		this.color = color2;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.drawRect((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public void setStartpoint(Point p1) {
		double x1 = p1.getX();
		double y1 = p1.getY();
		double x2 = x + width;
		double y2 = y + height;

		double left = Math.min(x1, x2);
		double top = Math.min(y1, y2);
		double right = Math.max(x1, x2);
		double bottom = Math.max(y1, y2);

		super.setFrame(left, top, right - left, bottom - top);
	}

	@Override
	public void setEndpoint(Point p2) {
		double x1 = x;
		double y1 = y;
		double x2 = p2.getX();
		double y2 = p2.getY();

		double left = Math.min(x1, x2);
		double top = Math.min(y1, y2);
		double right = Math.max(x1, x2);
		double bottom = Math.max(y1, y2);

		super.setFrame(left, top, right - left, bottom - top);
	}

	@Override
	public Point getStartpoint() {
		return new Point((int) x, (int) y);
	}

	@Override
	public Point getEndpoint() {
		return new Point((int) (x + width), (int) (y + height));
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Rectangle [color=" + color + "]";
	}

}

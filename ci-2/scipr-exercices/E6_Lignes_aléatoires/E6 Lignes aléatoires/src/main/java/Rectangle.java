import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Rectangle implements CustomShape {

	private Color color;
	private Point p1, p2;

	public Rectangle(Color color2, Point p1, Point p2) {
		this.color = color2;
		this.p1 = p1;
		this.p2 = p2;
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.drawRect(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y), Math.abs(p2.x - p1.x), Math.abs(p2.y - p1.y));
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	public Point getP2() {
		return p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	@Override
	public String toString() {
		return "Rectangle = " + color + " (" + p1 + "->" + p2 + ")";
	}

	@Override
	public void setStartpoint(Point p1) {
		this.p1 = p1;
	}

	@Override
	public void setEndpoint(Point p2) {
		this.p2 = p2;
	}

}

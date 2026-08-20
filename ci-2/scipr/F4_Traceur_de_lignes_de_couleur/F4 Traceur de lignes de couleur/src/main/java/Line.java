import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line {

	private Color color;
	private Point p1, p2;

	public Line(Color color2, Point p1, Point p2) {
		this.color = color2;
		this.p1 = p1;
		this.p2 = p2;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(color);
		g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
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
		return color + " (" + p1 + "->" + p2 + ")";
	}

}

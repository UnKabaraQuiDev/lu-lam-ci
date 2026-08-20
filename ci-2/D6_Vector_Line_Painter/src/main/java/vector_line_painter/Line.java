package vector_line_painter;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Line implements Drawable {

	private Color color = Color.BLACK;
	private float thickness = 2;
	private Point p0, p1;

	public Line(int x0, int y0, int x1, int y1) {
		p0 = new Point(x0, y0);
		p1 = new Point(x1, y1);
	}

	public Line(Point p0, Point p1) {
		this.p0 = p0;
		this.p1 = p1;
	}

	public Line(Color color, float thickness, Point p0, Point p1) {
		this.color = color;
		this.thickness = thickness;
		this.p0 = p0;
		this.p1 = p1;
	}
	
	public Line(int x0, int y0, int x1, int y1, Color color) {
		this(x0, y0, x1, y1);
		this.color = color;
	}

	@Override
	public void accept(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(thickness));
		g2d.setColor(color);
		g2d.drawLine((int) p0.getX(), (int) p0.getY(), (int) p1.getX(), (int) p1.getY());
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getThickness() {
		return thickness;
	}

	public void setThickness(float thickness) {
		this.thickness = thickness;
	}

	public Point getP0() {
		return p0;
	}

	public void setP0(Point p0) {
		this.p0 = p0;
	}

	public Point getP1() {
		return p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

	@Override
	public String toString() {
		return "Line [color=" + color + ", thickness=" + thickness + ", p0=" + p0 + ", p1=" + p1 + "]";
	}

}

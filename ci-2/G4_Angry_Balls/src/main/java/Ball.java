
import java.awt.Color;
import java.awt.Graphics;

public class Ball {

	protected double x = 0, y = 0;
	protected int radius = 0;
	protected Color color = null;

	public Ball(double x, double y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval((int) (x - radius), (int) (y - radius), 2 * radius, 2 * radius);
		g.setColor(Color.WHITE);
		g.fillOval((int) (x + radius / 4), (int) (y - radius / 2), radius / 4, radius / 4);
	}

	public boolean isTouching(Ball otherBall) {
		double otherX = otherBall.getX();
		double otherY = otherBall.getY();
		int otherRadius = otherBall.getRadius();

		double distance = Math.sqrt(Math.pow(otherX - x, 2) + Math.pow(otherY - y, 2));
		double radiusSum = radius + otherRadius;

		return distance <= radiusSum;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

}

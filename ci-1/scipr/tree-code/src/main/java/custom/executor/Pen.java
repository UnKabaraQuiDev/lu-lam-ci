package custom.executor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Pen {

	private final Graphics2D g2;

	private float x = 0;
	private float y = 0;
	private float angle = 0;
	private boolean down = false;

	public Pen(Graphics2D g2) {
		this.g2 = g2;
		g2.setStroke(new BasicStroke(1f));
		g2.setColor(Color.BLACK);
	}

	public void rotate(int degrees) {
		angle += degrees;
	}

	public void move(int pixels) {
		double rad = Math.toRadians(angle);
		float nx = x + (float) (Math.cos(rad) * pixels);
		float ny = y + (float) (Math.sin(rad) * pixels);

		if (down) {
			g2.draw(new Line2D.Float(x, y, nx, ny));
		}

		x = nx;
		y = ny;
	}

	public void up() {
		down = false;
	}

	public void down() {
		down = true;
	}

	public void state(boolean isDown) {
		down = isDown;
	}

	public void thickness(float t) {
		g2.setStroke(new BasicStroke(t));
	}

	public void color(Color c) {
		g2.setColor(c);
	}

	public void goto_(int x2, int y2) {
		x = x2;
		y = y2;
	}

}
package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MovingObject extends Rectangle {
	protected Color color;
	protected int dX = 0, dY = 0;

	public MovingObject(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}

	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	public void move(int gameWidth, int gameHeight) {
		x += dX;
		y += dY;
	}
}

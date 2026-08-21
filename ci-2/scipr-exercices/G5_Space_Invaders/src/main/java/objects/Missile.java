package objects;

import java.awt.Color;
import java.awt.Graphics;

public class Missile extends MovingObject {
	public Missile(int x, int y) {
		super(x, y, 5, 10, Color.YELLOW);
		dY = -3;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.RED);
		g.drawLine(x, y + height, x - 2, y + height + 5);
		g.drawLine(x + width, y + height, x + width + 2, y + height + 5);
	}
}
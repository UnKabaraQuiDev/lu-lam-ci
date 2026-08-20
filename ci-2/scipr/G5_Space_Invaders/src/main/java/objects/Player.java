package objects;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends MovingObject {
	public Player(int x, int y) {
		super(x, y, 30, 30, Color.ORANGE);
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.BLACK);
		g.fillRect(x + 5, y + 5, 20, 20);
	}
}

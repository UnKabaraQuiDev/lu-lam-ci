package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Invader extends MovingObject {

	public Invader(int x, int y) {
		super(x, y, 30, 30, Color.GREEN);
		Random rand = new Random();
		dX = rand.nextInt(5) - 2;
		dY = rand.nextInt(5) - 2;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.setColor(Color.BLACK);
		g.drawLine(x, y, x + width, y + height);
		g.drawLine(x + width, y, x, y + height);
	}
	
	@Override
	public void move(int gameWidth, int gameHeight) {
		super.move(gameWidth, gameHeight);
		

		if (x <= 0 || x + width >= gameWidth) {
			dX = -dX;
		}
		if (y <= 0 || y + height >= gameHeight - 100) {
			dY = -dY;
		}
	}
}

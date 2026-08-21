
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import objects.Invader;
import objects.Missile;
import objects.Player;

public class Game {

	private Player player;
	private Missile missile = null;
	private ArrayList<Invader> invaders = new ArrayList<>();
	private int width, height;

	public Game(int width, int height) {
		this.width = width;
		this.height = height;
		player = new Player(10, height - 30 * 2);
		Random rand = new Random();
		for (int i = 0; i < 10; i++) {
			invaders.add(new Invader(rand.nextInt(width - 30), rand.nextInt(height / 2)));
		}
	}

	public void draw(Graphics g) {
		player.draw(g);
		for (Invader inv : invaders)
			inv.draw(g);
		if (missile != null)
			missile.draw(g);
	}

	public void move() {
		for (Invader inv : invaders)
			inv.move(width, height);
		if (missile != null) {
			missile.move(width, height);
			if (missile.y + missile.height < 0)
				missile = null;
		}
		checkCollisions();
	}

	private void checkCollisions() {
		if (missile != null) {
			invaders.removeIf(inv -> missile.intersects(inv));
			if (invaders.isEmpty())
				missile = null;
		}
	}

	public void setPlayerX(int x) {
		player.x = Math.max(0, Math.min(width - player.width, x));
	}

	public boolean isOver() {
		return invaders.isEmpty();
	}

	public void launchMissile() {
		if (missile == null)
			missile = new Missile(player.x + 12, player.y);
	}
}

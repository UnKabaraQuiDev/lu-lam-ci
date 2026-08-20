
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import lu.pcy113.pclib.PCUtils;

public class Game {

	private int width = 0, height = 0;
	private MovingBall playerBall = null;
	private ArrayList<Ball> alBalls = new ArrayList<>();
	private Point mousePoint = null;
	private int redBallsCount = 0;

	public Game(int width, int height) {
		this.width = width;
		this.height = height;

		redBallsCount = 10;

		for (int i = 0; i < redBallsCount; i++) {
			int radius = 20;
			double x = PCUtils.randomIntRange(width / 2, width - 2 * radius);
			double y = PCUtils.randomIntRange(height / 4, height - 2 * radius);

			alBalls.add(new Ball(x, y, radius, Color.RED));
		}

		int radius = 20;
		double x = radius;
		double y = height - radius;

		playerBall = new MovingBall(x, y, radius, Color.GREEN);
		alBalls.add(playerBall);
	}

	public void setPlayerBallSteps() {
		if (mousePoint != null) {
			double dX = (mousePoint.x - playerBall.getX()) / 20;
			double dY = (mousePoint.y - playerBall.getY()) / 20;
			playerBall.setdX(dX);
			playerBall.setdY(dY);
		}
	}

	public void draw(Graphics g) {

		for (Ball ball : alBalls) {
			ball.draw(g);
		}

		g.setColor(Color.YELLOW);
		g.fillRect(width / 3, height / 2, 10, height / 2);

		if (mousePoint != null) {
			g.setColor(Color.WHITE);
			g.drawLine((int) playerBall.getX(), (int) playerBall.getY(), mousePoint.x, mousePoint.y);
		}
	}

	public int move() {

		playerBall.move();

		int r = playerBall.getRadius();
		if (((width / 3 <= playerBall.getX() + r) && (playerBall.getX() - r <= width / 3 + 10)) && (height / 2 <= playerBall.getY() + r)) {

			playerBall.setdX(0);
			playerBall.setX(width / 3 - r);
		}

		for (int i = 0; i < redBallsCount; i++) {
			Ball redBall = alBalls.get(i);
			if (redBall.isTouching(playerBall)) {
				alBalls.remove(i);
				redBallsCount--;
			}
		}

		if (playerBall.getX() - playerBall.getRadius() < 0 || playerBall.getX() + playerBall.getRadius() > width || playerBall.getY() + playerBall.getRadius() > height) {

			return 1;
		} else {
			return 0;
		}
	}

	public void playerBallReset() {
		int radius = 20;
		double x = radius;
		double y = height - radius;

		playerBall.setX(x);
		playerBall.setY(y);

		playerBall.setdX(0.0);
		playerBall.setdY(0.0);
	}

	public boolean isOver() {
		return alBalls.size() - 1 == 0;
	}

	public void setMousePosition(Point mousePoint) {
		this.mousePoint = mousePoint;
	}

}

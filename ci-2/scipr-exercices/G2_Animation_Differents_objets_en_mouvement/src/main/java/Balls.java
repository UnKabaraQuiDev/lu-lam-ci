import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;

import org.joml.Vector2d;

import lu.pcy113.pclib.PCUtils;

import balls.Ball;
import balls.EmoHappy;
import balls.EmoNeural;
import balls.EmoSmile;
import balls.EmoSurprised;
import balls.EmoTongue;
import balls.EmoUnSmile;

public class Balls {

	protected static final double DEFAULT_SPEED = 2;

	private List<Ball> balls = new ArrayList<>();

	public double speed = DEFAULT_SPEED;

	public Balls(List<Ball> circles) {
		this.balls = circles;
	}

	public Balls() {
		for (int i = 0; i < 50; i++) {
			addBall();
		}
	}

	public void fixedUpdate(double dTime, Vector2d viewportSize) {
		balls.forEach(b -> b.fixedUpdate(dTime * speed, viewportSize));
	}

	public void drawBalls(Graphics2D g2d, Vector2d viewportSize) {
		balls.forEach(b -> b.draw(g2d, viewportSize));
	}

	public void drawLine(Graphics2D g2d) {
		Polygon poly = new Polygon();

		balls.forEach(circle -> poly.addPoint((int) circle.getCenter().x, (int) circle.getCenter().y));

		g2d.draw(poly);
	}

	public List<Ball> getBalls() {
		return balls;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void addBall() {
		final int rand = PCUtils.randomIntRange(0, 6);
		switch (rand) {
		case 0:
			balls.add(new EmoSmile(new Vector2d(PCUtils.randomIntRange(0, 200), PCUtils.randomIntRange(0, 200)), new Vector2d(PCUtils.randomDoubleRange(10, 300), PCUtils.randomDoubleRange(10, 300)),
					300, 50, PCUtils.randomColor(false)));
			break;
		case 1:
			balls.add(new EmoSurprised(new Vector2d(PCUtils.randomIntRange(0, 200), PCUtils.randomIntRange(0, 200)),
					new Vector2d(PCUtils.randomDoubleRange(10, 300), PCUtils.randomDoubleRange(10, 300)), 300, 50, PCUtils.randomColor(false)));
			break;
		case 2:
			balls.add(new EmoUnSmile(new Vector2d(PCUtils.randomIntRange(0, 200), PCUtils.randomIntRange(0, 200)), new Vector2d(PCUtils.randomDoubleRange(10, 300), PCUtils.randomDoubleRange(10, 300)),
					300, 50, PCUtils.randomColor(false)));
			break;
		case 3:
			balls.add(new EmoHappy(new Vector2d(PCUtils.randomIntRange(0, 200), PCUtils.randomIntRange(0, 200)), new Vector2d(PCUtils.randomDoubleRange(10, 300), PCUtils.randomDoubleRange(10, 300)),
					300, 50, PCUtils.randomColor(false)));
			break;
		case 4:
			balls.add(new EmoNeural(new Vector2d(PCUtils.randomIntRange(0, 200), PCUtils.randomIntRange(0, 200)), new Vector2d(PCUtils.randomDoubleRange(10, 300), PCUtils.randomDoubleRange(10, 300)),
					300, 50, PCUtils.randomColor(false)));
			break;
		case 5:
			balls.add(new EmoTongue(new Vector2d(PCUtils.randomIntRange(0, 200), PCUtils.randomIntRange(0, 200)), new Vector2d(PCUtils.randomDoubleRange(10, 300), PCUtils.randomDoubleRange(10, 300)),
					300, 50, PCUtils.randomColor(false)));
			break;
		}
	}

}

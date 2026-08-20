import java.awt.Color;
import java.awt.Graphics2D;

import org.joml.Vector2d;

import lu.pcy113.pclib.PCUtils;

public class Ball {

	private static final Vector2d DOWN_FORCE = new Vector2d(0, 100); // px/s^2
	private static final double FRICTION = PCUtils.randomDoubleRange(0.65, 0.8);

	// doesn't apply gravity
	private boolean fixed = true;

	private Vector2d center;
	private Vector2d velocity;
	private Vector2d acceleration = new Vector2d(0);

	private int radius;
	private Color color;

	public Ball(Vector2d center, Vector2d velocity, int radius, Color color) {
		this.center = center;
		this.velocity = velocity;
		this.radius = radius;
		this.color = color;
	}

	public void fixedUpdate(double dTime, Vector2d size) {
		if (!fixed) {
			acceleration.add(DOWN_FORCE.mul(dTime, new Vector2d())); // apply gravity
		}

		velocity.add(acceleration);

		center.add(velocity.mul(dTime, new Vector2d()));

		if (center.x - radius < 0) {
			velocity.x = -velocity.x * FRICTION;
			center.x = radius;
		} else if (center.x + radius > size.x) {
			velocity.x = -velocity.x * FRICTION;
			center.x = size.x - radius;
		}

		if (center.y - radius < 0) {
			velocity.y = -velocity.y * FRICTION;
			center.y = radius;
		} else if (center.y + radius > size.y) {
			velocity.y = -velocity.y * FRICTION;
			center.y = size.y - radius;
		}

	}

	public void draw(Graphics2D g2d, Vector2d viewportSize) {
		Color initialColor = new Color((int) PCUtils.clamp(0, 255, center.x / viewportSize.x * 255), (int) PCUtils.clamp(0, 255, center.y / viewportSize.y * 255), 0);

		final int LAYER_COUNT = 5;
		for (int layer = LAYER_COUNT; layer > 0; layer--) {
			int radius = this.radius / LAYER_COUNT* layer ;
			g2d.setColor(initialColor);
			g2d.fillOval((int) (center.x - radius), (int) (center.y - radius), (int) (radius * 2), (int) (radius * 2));
			initialColor = initialColor.brighter();
		}
	}

	public Vector2d getCenter() {
		return center;
	}

	public void setCenter(Vector2d center) {
		this.center = center;
	}

	public Vector2d getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2d velocity) {
		this.velocity = velocity;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isFixed() {
		return fixed;
	}

	public void setFixed(boolean animation) {
		this.fixed = animation;
	}

}

import java.awt.Color;
import java.awt.Graphics2D;

import org.joml.Vector2f;

import lu.pcy113.pclib.PCUtils;

public class Ball {

	public static final long SICK_TIMEOUT = 10000;

	private Vector2f position;
	private Vector2f velocity;
	private boolean locked = false;
	private float radius;
	private DiseaseState state = DiseaseState.HEALTHY;
	private Long infectedTime = null;

	public Ball(int x, int y, int radius) {
		this.position = new Vector2f(x, y);
		this.velocity = new Vector2f(0, 0);
		this.radius = radius;
	}

	public Ball(Vector2f position, Vector2f velocity, int radius) {
		this.position = position;
		this.velocity = velocity;
		this.radius = radius;

		this.state = Math.random() < 0.1f ? DiseaseState.SICK : DiseaseState.HEALTHY;
		this.locked = state == DiseaseState.SICK ? false : radius > 8 && Math.random() > 0.05f;
		this.infectedTime = state == DiseaseState.SICK ? System.currentTimeMillis()+ PCUtils.randomLongRange(5000, 20000) : null;
	}

	public Ball(Vector2f position, Vector2f velocity, int radius, DiseaseState state) {
		this.position = position;
		this.velocity = velocity;
		this.radius = radius;
		this.state = state;
	}

	public Vector2f getPosition() {
		return position;
	}

	public void setPosition(Vector2f position) {
		this.position.set(position);
	}

	public Vector2f getRotation() {
		return velocity;
	}

	public void setRotation(Vector2f rotation) {
		this.velocity.set(rotation);
	}

	public float getRadius() {
		return radius;
	}

	public DiseaseState getState() {
		return state;
	}

	public void setState(DiseaseState state) {
		this.state = state;
	}

	public boolean collidesWith(Ball other) {
		if (state == DiseaseState.DEAD || other.state == DiseaseState.DEAD) {
			return false;
		}

		return other.position.distance(this.position) < radius + other.radius;
	}

	public boolean bounce(Ball other) {
		if (!collidesWith(other) || this.state == DiseaseState.DEAD || other.state == DiseaseState.DEAD)
			return false;

		if ((other.state == DiseaseState.SICK || this.state == DiseaseState.SICK)
				&& !(other.state == DiseaseState.IMUNE || this.state == DiseaseState.IMUNE)) {
			other.state = DiseaseState.SICK;
			this.state = DiseaseState.SICK;
			other.infectedTime = other.infectedTime == null ? System.currentTimeMillis() + PCUtils.randomIntRange(10000, 20000)
					: other.infectedTime;
			this.infectedTime = this.infectedTime == null ? System.currentTimeMillis() + PCUtils.randomIntRange(10000, 20000)
					: this.infectedTime;
		}

		Vector2f delta = new Vector2f(this.position).sub(other.position);
		float distance = delta.length();

		if (distance == 0f) {
			delta.set(1f, 0f);
			distance = 1f;
		}

		Vector2f normal = new Vector2f(delta).div(distance);

		float v1n = this.velocity.dot(normal);
		float v2n = other.velocity.dot(normal);

		float temp = v1n;
		v1n = v2n;
		v2n = temp;

		this.velocity.add(new Vector2f(normal).mul(v1n - this.velocity.dot(normal)));
		other.velocity.add(new Vector2f(normal).mul(v2n - other.velocity.dot(normal)));

		float overlap = this.radius + other.radius - distance;
		if (overlap > 0) {
			Vector2f correction = new Vector2f(normal).mul(overlap / 2f);
			this.position.add(correction);
			other.position.sub(correction);
		}

		if (this.canHaveKids() && other.canHaveKids()) {
			return Math.random() < 0.01f;
		}

		return false;
	}

	private boolean canHaveKids() {
		return /* (this.state == DiseaseState.IMUNE || this.state == DiseaseState.HEALTHY) && */ state != DiseaseState.DEAD
				&& this.radius >= 9;
	}

	public void doPhysics(float dt, float width, float height) {
		if (!locked) {
			position.fma(dt, velocity);
			checkWallCollision(width, height);
		}

		if (state != DiseaseState.DEAD) {
			radius = PCUtils.clamp(0, 15, radius + 0.001f);
			if (radius == 15) {
				state = DiseaseState.DEAD;
				velocity.zero();
				infectedTime = System.currentTimeMillis() + 5000;
				return;
			}
		}

		if (infectedTime == null || state == DiseaseState.DEAD) {
			return;
		}
		if (System.currentTimeMillis() > infectedTime && state == DiseaseState.SICK) {
			if (Math.random() < (0.1f * PCUtils.map(radius, 5, 10, 10, 1))) {
				state = DiseaseState.DEAD;
				velocity.zero();
				infectedTime = System.currentTimeMillis() + PCUtils.randomLongRange(10000, 20000);
			} else {
				state = DiseaseState.IMUNE;
				infectedTime = System.currentTimeMillis() + PCUtils.randomLongRange(10000, 20000);
			}
		} else if (System.currentTimeMillis() > infectedTime && state == DiseaseState.IMUNE) {
			state = DiseaseState.HEALTHY;
			this.infectedTime = null;
		}

	}

	private void checkWallCollision(float width, float height) {
		if (position.x - radius < 0) {
			position.x = radius;
			if (velocity.x < 0)
				velocity.x = -velocity.x;
		}
		if (position.x + radius > width) {
			position.x = width - radius;
			if (velocity.x > 0)
				velocity.x = -velocity.x;
		}
		if (position.y - radius < 0) {
			position.y = radius;
			if (velocity.y < 0)
				velocity.y = -velocity.y;
		}
		if (position.y + radius > height) {
			position.y = height - radius;
			if (velocity.y > 0)
				velocity.y = -velocity.y;
		}
	}

	public void move(int dx, int dy) {
		this.position.add(dx, dy);
	}

	public void rotate(int rx, int ry) {
		this.velocity.add(rx, ry);
	}

	@Override
	public String toString() {
		return "Ball [position=" + position + ", rotation=" + velocity + ", radius=" + radius + "]";
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(state.getColor());
		g2d.fillOval((int) (position.x - radius), (int) (position.y - radius), (int) radius * 2, (int) radius * 2);
		if (canHaveKids()) {
			g2d.setColor(Color.CYAN);
			g2d.drawOval((int) (position.x - radius), (int) (position.y - radius), (int) radius * 2, (int) radius * 2);
		}
	}

	public Long getInfectedTime() {
		return infectedTime;
	}

	public boolean isInside(int x, int y) {
		return position.distance(new Vector2f(x, y)) <= radius;
	}

}
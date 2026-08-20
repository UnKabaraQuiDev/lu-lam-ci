package balls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import org.joml.Vector2d;

public class Emoticon extends Ball {

	public Emoticon(Vector2d center, Vector2d velocity, double speed, int radius, Color color) {
		super(center, velocity, speed, radius, color);
	}

	@Override
	public void draw(Graphics2D g2d, Vector2d viewportSize) {
		super.draw(g2d, viewportSize);

		g2d.setColor(Color.YELLOW);
		g2d.fillOval((int) (super.getCenter().x - super.getRadius()), (int) (super.getCenter().y - super.getRadius()), super.getRadius() * 2, super.getRadius() * 2);

		final Ellipse2D.Double eye1 = new Ellipse2D.Double((int) (super.getCenter().x - super.getRadius() / 2), (int) (super.getCenter().y - super.getRadius() / 2), (int) (super.getRadius() / 2),
				(int) (super.getRadius() / 2));
		g2d.setColor(Color.WHITE);
		g2d.fill(eye1);
		g2d.setColor(Color.BLACK);
		g2d.draw(eye1);

		final Ellipse2D.Double eye2 = new Ellipse2D.Double((int) (super.getCenter().x + super.getRadius() / 2), (int) (super.getCenter().y - super.getRadius() / 2), (int) (super.getRadius() / 2),
				(int) (super.getRadius() / 2));
		g2d.setColor(Color.WHITE);
		g2d.fill(eye2);
		g2d.setColor(Color.BLACK);
		g2d.draw(eye2);

		final Ellipse2D.Double eyeIn1 = new Ellipse2D.Double((int) (super.getCenter().x - super.getRadius() / 3), (int) (super.getCenter().y - super.getRadius() / 3), (int) (super.getRadius() / 4),
				(int) (super.getRadius() / 4));
		g2d.setColor(Color.BLUE);
		g2d.fill(eyeIn1);

		final Ellipse2D.Double eyeIn2 = new Ellipse2D.Double((int) (super.getCenter().x + super.getRadius() / 3 * 2), (int) (super.getCenter().y - super.getRadius() / 3),
				(int) (super.getRadius() / 4), (int) (super.getRadius() / 4));
		g2d.setColor(Color.BLUE);
		g2d.fill(eyeIn2);
	}

}

package balls;

import java.awt.Color;
import java.awt.Graphics2D;

import org.joml.Vector2d;

public class EmoTongue extends Emoticon {

	public EmoTongue(Vector2d center, Vector2d velocity, double speed, int radius, Color color) {
		super(center, velocity, speed, radius, color);
	}

	@Override
	public void draw(Graphics2D g2d, Vector2d viewportSize) {
		super.draw(g2d, viewportSize);

		g2d.setColor(Color.BLACK);
		g2d.drawLine((int) (super.getCenter().x - getRadius() / 2), (int) (super.getCenter().y + getRadius() / 3), (int) (super.getCenter().x + getRadius() / 2),
				(int) (super.getCenter().y + getRadius() / 3));
		
		g2d.setColor(Color.RED);
		g2d.fillArc((int) (super.getCenter().x - super.getRadius() / 4), (int) (super.getCenter().y + getRadius() / 3 - getRadius() / 4), (int) (getRadius() / 2), (int) (getRadius() / 2), 0, -180);
	}

}

package balls;

import java.awt.Color;
import java.awt.Graphics2D;

import org.joml.Vector2d;

public class EmoNeural extends Emoticon {

	public EmoNeural(Vector2d center, Vector2d velocity, double speed, int radius, Color color) {
		super(center, velocity, speed, radius, color);
	}

	@Override
	public void draw(Graphics2D g2d, Vector2d viewportSize) {
		super.draw(g2d, viewportSize);

		g2d.setColor(Color.BLACK);
		g2d.drawLine((int) (super.getCenter().x - getRadius() / 2), (int) (super.getCenter().y + getRadius() / 2), (int) (super.getCenter().x + getRadius() / 2),
				(int) (super.getCenter().y + getRadius() / 2));
	}

}

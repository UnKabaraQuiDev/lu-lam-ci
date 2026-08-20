package balls;

import java.awt.Color;
import java.awt.Graphics2D;

import org.joml.Vector2d;

public class EmoUnSmile extends Emoticon {

	public EmoUnSmile(Vector2d center, Vector2d velocity, double speed, int radius, Color color) {
		super(center, velocity, speed, radius, color);
	}

	@Override
	public void draw(Graphics2D g2d, Vector2d viewportSize) {
		super.draw(g2d, viewportSize);

		g2d.setColor(Color.BLACK);
		g2d.drawArc((int) super.getCenter().x, (int) super.getCenter().y, (int) (super.getRadius() / 2), (int) (super.getRadius() / 2), 0, 180);
	}

}

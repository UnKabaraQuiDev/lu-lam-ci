import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.function.BiConsumer;

import javax.swing.JPanel;

public class AnalogWatch implements BiConsumer<JPanel, Graphics2D> {

	@Override
	public void accept(JPanel panel, Graphics2D g2d) {
		final double width = panel.getWidth(), height = panel.getHeight();
		final double halfWidth = width / 2, halfHeight = height / 2;
		final double minSize = Math.min(width, height), halfMinSize = Math.min(halfWidth, halfHeight);

		g2d.translate(halfWidth, halfHeight);

		final Ellipse2D.Double circle = new Ellipse2D.Double(-halfMinSize, -halfMinSize, minSize, minSize);
		g2d.setColor(Color.YELLOW);
		g2d.fill(circle);
		g2d.setStroke(new BasicStroke(2));
		g2d.setColor(Color.BLUE);
		g2d.draw(circle);
		
		final int STEP_COUNT = 60, LENGTH = 10;
		for (int i = 0; i < STEP_COUNT; i++) {
			g2d.drawLine((int) halfMinSize, 0, (int) (halfMinSize - (i % 5 == 0 ? 2 : 1) * LENGTH), 0);
			g2d.rotate(2 * Math.PI / STEP_COUNT);
		}

		final LocalTime currentTime = LocalTime.now();

		final int hours = currentTime.getHour() % 12;
		final int minutes = currentTime.getMinute();
		final int seconds = currentTime.getSecond();

		final double secAngle = seconds * 6.0 - 90;
		final double minAngle = (minutes + seconds / 60.0) * 6.0 - 90;
		final double hourAngle = (hours + minutes / 60.0 + seconds / 3600.0) * 30.0 - 90;

		g2d.setColor(Color.RED);
		g2d.draw(rotateLine(new Line2D.Double(0, 0, halfMinSize - LENGTH, 0), Math.toRadians(secAngle)));

		g2d.setStroke(new BasicStroke(7));
		g2d.setColor(Color.BLUE);
		g2d.draw(rotateLine(new Line2D.Double(0, 0, halfMinSize * 0.6, 0), Math.toRadians(hourAngle)));
		g2d.draw(rotateLine(new Line2D.Double(0, 0, halfMinSize * 0.8, 0), Math.toRadians(minAngle)));

		g2d.drawString(LocalDate.now().toString(), (int) -halfWidth + 5, (int) halfHeight - 5);
	}

	private static Line2D.Double rotateLine(Line2D.Double line, double radianAngle) {
		double x1 = line.x1 * Math.cos(radianAngle) - line.y1 * Math.sin(radianAngle);
		double y1 = line.x1 * Math.sin(radianAngle) + line.y1 * Math.cos(radianAngle);

		double x2 = line.x2 * Math.cos(radianAngle) - line.y2 * Math.sin(radianAngle);
		double y2 = line.x2 * Math.sin(radianAngle) + line.y2 * Math.cos(radianAngle);

		return new Line2D.Double(x1, y1, x2, y2);
	}
	
	public static Point2D.Double polar2cart(Point.Double center, double radius, double angle) {
        double x = center.x + radius * Math.cos(angle);
        double y = center.y + radius * Math.sin(angle);
        
        return new Point.Double(x, y);
    }

}

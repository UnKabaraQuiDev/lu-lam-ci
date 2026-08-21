
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawPanel extends JPanel {

	private static final int TIMER_DELAY_MS = 16;
	private static final double BASE_RADIUS = 120.0;
	private static final int MIN_CIRCLES = 1;
	private static final int MAX_TRACE_POINTS = 20000;
	private static final double TIME_STEP = 0.03;

	private int circleCount = 4;
	private double radiusRatio = 0.60;
	private double rotationSpeed = 0.35;
	private double time = 0.0;
	private boolean alternateDirection = false;
	private int sampleCount = 1;

	private final List<Point2D.Double> tracePoints = new ArrayList<>();
	private final Timer timer;

	public DrawPanel() {
		setPreferredSize(new Dimension(1000, 700));
		setBackground(Color.WHITE);

		timer = new Timer(TIMER_DELAY_MS, e -> {
			double previousTime = time;
			double deltaTime = TIME_STEP * rotationSpeed;

			time += deltaTime;

			int samples = Math.max(1, sampleCount);

			for (int i = 1; i <= samples; i++) {
				double t = previousTime + (deltaTime * i / samples);
				Point2D.Double tip = computeLastCenterAtTime(t);
				tracePoints.add(tip);
			}

			trimTrace();
			repaint();
		});
		timer.start();
	}

	public void addCircle() {
		circleCount++;
		repaint();
	}

	public void removeLastCircle() {
		if (circleCount > MIN_CIRCLES) {
			circleCount--;
			repaint();
		}
	}

	public void setRadiusRatio(double radiusRatio) {
		this.radiusRatio = radiusRatio;
		repaint();
	}

	public void setRotationSpeed(double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}

	public void setSampleCount(int sampleCount) {
		this.sampleCount = Math.max(1, sampleCount);
	}

	public void setAlternateDirection(boolean alternateDirection) {
		this.alternateDirection = alternateDirection;
		repaint();
	}

	public List<Point2D.Double> getTracePoints() {
		return tracePoints;
	}

	private void trimTrace() {
		while (tracePoints.size() > MAX_TRACE_POINTS) {
			tracePoints.remove(0);
		}
	}

	private Point2D.Double computeLastCenter() {
		return computeLastCenterAtTime(time);
	}

	private Point2D.Double computeLastCenterAtTime(double currentTime) {
		double centerX = getWidth() / 2.0;
		double centerY = getHeight() / 2.0;

		double parentX = centerX;
		double parentY = centerY;
		double parentRadius = BASE_RADIUS;

		for (int i = 0; i < circleCount; i++) {
			double currentRadius = parentRadius * radiusRatio;
			double direction = getDirectionForCircle(i);

			double angle = currentTime * (i + 1) * direction;

			double currentX = parentX + Math.cos(angle) * (parentRadius + currentRadius);
			double currentY = parentY + Math.sin(angle) * (parentRadius + currentRadius);

			parentX = currentX;
			parentY = currentY;
			parentRadius = currentRadius;
		}

		return new Point2D.Double(parentX, parentY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		double rootX = getWidth() / 2.0;
		double rootY = getHeight() / 2.0;

		double parentX = rootX;
		double parentY = rootY;
		double parentRadius = BASE_RADIUS;

		g2.setColor(new Color(180, 180, 180));
		drawCircle(g2, parentX, parentY, parentRadius);

		Point2D.Double lastCenter = null;

		for (int i = 0; i < circleCount; i++) {
			double currentRadius = parentRadius * radiusRatio;
			double direction = getDirectionForCircle(i);
			double angle = time * (i + 1) * direction;

			double currentX = parentX + Math.cos(angle) * (parentRadius + currentRadius);
			double currentY = parentY + Math.sin(angle) * (parentRadius + currentRadius);

			g2.setColor(new Color(120, 120, 120));
			g2.setStroke(new BasicStroke(1.5f));
			g2.drawLine((int) Math.round(parentX), (int) Math.round(parentY), (int) Math.round(currentX), (int) Math.round(currentY));

			g2.setColor(new Color(80, 80, 80));
			drawCircle(g2, currentX, currentY, currentRadius);

			g2.setColor(Color.RED);
			g2.fill(new Ellipse2D.Double(currentX - 3, currentY - 3, 6, 6));

			parentX = currentX;
			parentY = currentY;
			parentRadius = currentRadius;
			lastCenter = new Point2D.Double(currentX, currentY);
		}

		g2.setColor(Color.BLUE);
		for (Point2D.Double p : tracePoints) {
			g2.fillRect((int) Math.round(p.x), (int) Math.round(p.y), 2, 2);
		}

		if (lastCenter != null) {
			g2.setColor(Color.BLUE);
			g2.fill(new Ellipse2D.Double(lastCenter.x - 4, lastCenter.y - 4, 8, 8));
		}

		g2.dispose();
	}

	private void drawCircle(Graphics2D g2, double centerX, double centerY, double radius) {
		g2.draw(new Ellipse2D.Double(centerX - radius, centerY - radius, radius * 2, radius * 2));
	}

	private double getDirectionForCircle(int index) {
		if (!alternateDirection) {
			return 1.0;
		}
		return (index % 2 == 0) ? 1.0 : -1.0;
	}

}
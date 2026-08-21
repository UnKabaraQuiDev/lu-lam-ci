import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Optional;

public class SelectTool implements Tool {

	private static final double DIST = 25;

	private Color previousColor;

	private Point offset;
	private boolean startpointSelected;
	private boolean endpointSelected;

	@Override
	public void onDragged(MouseEvent e, DrawPanel panel) {
		if (panel.getCurrentShape() != null) {
			final CustomShape shape = panel.getCurrentShape();
			final Point startpoint = shape.getStartpoint();
			final Point endpoint = shape.getEndpoint();

			if (startpoint.distance(e.getPoint()) < DIST || startpointSelected) {
				startpointSelected = true;
				shape.setStartpoint(e.getPoint());
			} else if (endpoint.distance(e.getPoint()) < DIST || endpointSelected) {
				endpointSelected = true;
				shape.setEndpoint(e.getPoint());
			} else if (offset != null) {
				int newX = e.getX() - offset.x;
				int newY = e.getY() - offset.y;

				int dx = newX - startpoint.x;
				int dy = newY - startpoint.y;

				shape.setStartpoint(new Point(startpoint.x + dx, startpoint.y + dy));
				shape.setEndpoint(new Point(endpoint.x + dx, endpoint.y + dy));
			}
		}
	}

	@Override
	public void onReleased(MouseEvent e, DrawPanel panel) {
		offset = null;
		endpointSelected = false;
		startpointSelected = false;
	}

	@Override
	public void onClick(MouseEvent e, DrawPanel panel) {
		final Point2D p = e.getPoint();

		final Optional<CustomShape> newShape = panel
				.getShapes()
				.getShapes()
				.parallelStream()
				.filter(shape -> shape.getBounds2D().contains(p))
				.findFirst();

		if (panel.getCurrentShape() == null && newShape.isPresent()) {
			panel.setCurrentShape(newShape.get());
			previousColor = newShape.get().getColor();
			newShape.get().setColor(Color.RED);
		} else if (panel.getCurrentShape() != null && newShape.isPresent() && newShape.get() != panel.getCurrentShape()) {
			panel.getCurrentShape().setColor(previousColor);
			panel.setCurrentShape(newShape.get());
			previousColor = newShape.get().getColor();
			newShape.get().setColor(Color.RED);
		} else if (panel.getCurrentShape() != null && newShape.isEmpty()) {
			panel.getCurrentShape().setColor(previousColor);
			panel.setCurrentShape(null);
			previousColor = null;
		}

		if (panel.getCurrentShape() == null)
			return;

		final Point startpoint = panel.getCurrentShape().getStartpoint();
		final Point point = e.getPoint();
		offset = new Point(point.x - startpoint.x, point.y - startpoint.y);
	}

	@Override
	public void onDeinit(DrawPanel panel) {
		panel.getCurrentShape().setColor(previousColor);
		previousColor = null;
	}

}

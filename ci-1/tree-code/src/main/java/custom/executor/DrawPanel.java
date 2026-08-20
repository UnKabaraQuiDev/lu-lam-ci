package custom.executor;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.function.Consumer;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private Consumer<Pen> executor;

	private double zoom = 1.0;
	private double offsetX = 0;
	private double offsetY = 0;

	private Point lastDrag = null;

	public DrawPanel() {
		final MouseAdapter mouse = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lastDrag = e.getPoint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				final Point p = e.getPoint();
				offsetX += (p.x - lastDrag.x) / zoom;
				offsetY += (p.y - lastDrag.y) / zoom;
				lastDrag = p;
				repaint();
			}

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				final double scaleFactor = 1.1;
				final double oldZoom = zoom;
				if (e.getPreciseWheelRotation() < 0) {
					zoom *= scaleFactor;
				} else {
					zoom /= scaleFactor;
				}

				// adjust offset to zoom toward cursor
				final double mx = e.getX();
				final double my = e.getY();
				offsetX = mx - (mx - offsetX) * (zoom / oldZoom);
				offsetY = my - (my - offsetY) * (zoom / oldZoom);

				repaint();
			}
		};

		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addMouseWheelListener(mouse);
	}

	public void setExecutor(Consumer<Pen> executor) {
		this.executor = executor;
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (executor == null)
			return;

		final Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final AffineTransform at = new AffineTransform();
		at.translate(offsetX, offsetY);
		at.scale(zoom, zoom);
		g2.transform(at);

		executor.accept(new Pen(g2));

		g2.dispose();
	}

}
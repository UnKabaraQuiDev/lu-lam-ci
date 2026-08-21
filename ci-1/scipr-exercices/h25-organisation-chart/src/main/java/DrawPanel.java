import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 5955596628620424647L;

	private final OrganizationChart chart;

	private static final int BOX_W = 180;
	private static final int BOX_H = 60;

	private static final int TOP_MARGIN = 20;
	private static final int SIDE_MARGIN = 20;

	private static final int LEVEL_GAP = 70;
	private static final int H_GAP = 25;

	private boolean layoutDirty = true;
	private final Map<Person, Rectangle> boxes = new HashMap<>();
	private final Map<Person, Integer> subtreeWidth = new HashMap<>();
	private int preferredW = 2 * SIDE_MARGIN;
	private int preferredH = 2 * TOP_MARGIN;

	public DrawPanel(final OrganizationChart chart) {
		this.chart = Objects.requireNonNull(chart);
		this.setBackground(Color.WHITE);
		this.setOpaque(true);
	}

	public void invalidateLayout() {
		this.layoutDirty = true;
		this.revalidate();
		this.repaint();
	}

	@Override
	public void invalidate() {
		super.invalidate();
		this.invalidateLayout();
	}

	@Override
	public Dimension getPreferredSize() {
		this.doLayout();
		return new Dimension(this.preferredW, this.preferredH);
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		final Person root = this.chart.getRoot();
		if (root == null) {
			return;
		}

		this.doLayout();

		final Graphics2D g2 = (Graphics2D) g.create();
		try {
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2.setStroke(new BasicStroke(2f));
			g2.setColor(new Color(60, 60, 60));
			this.drawEdges(g2, root);

			for (final Map.Entry<Person, Rectangle> e : this.boxes.entrySet()) {
				this.drawPersonBox(g2, e.getKey(), e.getValue());
			}
		} finally {
			g2.dispose();
		}
	}

	@Override
	public void doLayout() {
		if (!this.layoutDirty) {
			return;
		}

		this.boxes.clear();
		this.subtreeWidth.clear();

		final Person root = this.chart.getRoot();
		if (root == null) {
			this.preferredW = 2 * SIDE_MARGIN;
			this.preferredH = 2 * TOP_MARGIN;
			this.layoutDirty = false;
			return;
		}

		final int rootW = this.computeSubtreeWidth(root);

		final int rootCenterX = SIDE_MARGIN + rootW / 2;
		this.layoutPositions(root, rootCenterX, 0);

		final Rectangle bounds = this.computeBounds();
		this.preferredW = bounds.width + 2 * SIDE_MARGIN;
		this.preferredH = bounds.height + 2 * TOP_MARGIN;

		this.layoutDirty = false;
	}

	private int computeSubtreeWidth(final Person node) {
		final List<Person> children = new ArrayList<>(node.getSubordinates());
		if (children.isEmpty()) {
			this.subtreeWidth.put(node, BOX_W);
			return BOX_W;
		}

		int sum = 0;
		for (final Person c : children) {
			sum += this.computeSubtreeWidth(c);
		}

		final int gaps = H_GAP * (children.size() - 1);
		final int needed = sum + gaps;

		final int w = Math.max(BOX_W, needed);
		this.subtreeWidth.put(node, w);
		return w;
	}

	private void layoutPositions(final Person node, final int centerX, final int level) {
		final int y = TOP_MARGIN + level * (BOX_H + LEVEL_GAP);
		final int x = centerX - BOX_W / 2;
		this.boxes.put(node, new Rectangle(x, y, BOX_W, BOX_H));

		final List<Person> children = new ArrayList<>(node.getSubordinates());
		if (children.isEmpty()) {
			return;
		}

		final int thisSubW = this.subtreeWidth.getOrDefault(node, BOX_W);

		final int left = centerX - thisSubW / 2;

		int childrenTotal = 0;
		for (final Person c : children) {
			childrenTotal += this.subtreeWidth.get(c);
		}
		childrenTotal += H_GAP * (children.size() - 1);

		final int childLeft = left + (thisSubW - childrenTotal) / 2;

		int cursor = childLeft;
		for (final Person c : children) {
			final int cw = this.subtreeWidth.get(c);
			final int childCenter = cursor + cw / 2;
			this.layoutPositions(c, childCenter, level + 1);
			cursor += cw + H_GAP;
		}
	}

	private Rectangle computeBounds() {
		int minX = Integer.MAX_VALUE;
		int minY = Integer.MAX_VALUE;
		int maxX = Integer.MIN_VALUE;
		int maxY = Integer.MIN_VALUE;

		for (final Rectangle r : this.boxes.values()) {
			minX = Math.min(minX, r.x);
			minY = Math.min(minY, r.y);
			maxX = Math.max(maxX, r.x + r.width);
			maxY = Math.max(maxY, r.y + r.height);
		}

		if (minX == Integer.MAX_VALUE) {
			return new Rectangle(0, 0, 0, 0);
		}
		return new Rectangle(minX, minY, maxX - minX, maxY - minY);
	}

	private void drawEdges(final Graphics2D g2, final Person parent) {
		final Rectangle pr = this.boxes.get(parent);
		if (pr == null) {
			return;
		}

		final int x1 = pr.x + pr.width / 2;
		final int y1 = pr.y + pr.height;

		for (final Person child : parent.getSubordinates()) {
			final Rectangle cr = this.boxes.get(child);
			if (cr == null) {
				continue;
			}

			final int x2 = cr.x + cr.width / 2;
			final int y2 = cr.y;

			final int midY = (y1 + y2) / 2;
			g2.drawLine(x1, y1, x1, midY);
			g2.drawLine(x1, midY, x2, midY);
			g2.drawLine(x2, midY, x2, y2);

			this.drawEdges(g2, child);
		}
	}

	private void drawPersonBox(final Graphics2D g2, final Person p, final Rectangle r) {
		g2.setColor(Color.CYAN);
		g2.fillRect(r.x, r.y, r.width, r.height);

		g2.setColor(Color.BLUE);
		g2.drawRect(r.x, r.y, r.width, r.height);

		final Shape oldClip = g2.getClip();
		final int pad = 10;
		g2.setClip(new Rectangle(r.x + pad, r.y + pad, r.width - 2 * pad, r.height - 2 * pad));
		try {
			final String line1 = p.getTitle();
			final String line2 = (p.getFirstName() + " " + p.getSurname()).trim();

			final Font base = g2.getFont();
			g2.setColor(Color.BLACK);

			g2.setFont(base.deriveFont(Font.BOLD, 13f));
			g2.drawString(line1, r.x + pad, r.y + 22);

			g2.setFont(base.deriveFont(Font.PLAIN, 12f));
			g2.drawString(line2, r.x + pad, r.y + 42);

			g2.setFont(base);
		} finally {
			g2.setClip(oldClip);
		}
	}

}
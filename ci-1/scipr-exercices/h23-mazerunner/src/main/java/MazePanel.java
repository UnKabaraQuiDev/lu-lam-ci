import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class MazePanel extends JPanel {

	private int cols = 7;
	private int rows = 5;

	private boolean[][] blocked = new boolean[rows][cols];

	private SolvedPath orangePath = null;

	private static final Color ORANGE = new Color(255, 200, 0);

	MazePanel() {
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.GRAY));

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				handleMouse(e);
			}
		});
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}

	public void setGridSize(final int cols, final int rows) {
		this.cols = cols;
		this.rows = rows;
		this.blocked = new boolean[rows][cols];
		this.orangePath = null;
		revalidate();
		repaint();
	}

	public void randomFill(final int percent) {
		final Random rnd = new Random();

		for (int y = 0; y < rows; y++) {
			Arrays.fill(blocked[y], false);
		}

		final int targetBlack = (int) Math.round(cols * rows * (percent / 100.0));
		int blackCount = 0;

		while (blackCount < targetBlack) {
			final int x = rnd.nextInt(cols);
			final int y = rnd.nextInt(rows);

			if ((x == 0 && y == 0) || (x == cols - 1 && y == rows - 1)) {
				continue;
			}

			if (!blocked[y][x]) {
				blocked[y][x] = true;
				blackCount++;
			}
		}

		blocked[0][0] = false;
		blocked[rows - 1][cols - 1] = false;

		orangePath = null;
		repaint();
	}

	public boolean isBlocked(final int x, final int y) {
		return blocked[y][x];
	}

	public void setOrangePath(final SolvedPath orangePath) {
		this.orangePath = orangePath;
		repaint();
	}

	private void handleMouse(final MouseEvent e) {
		if (cols <= 0 || rows <= 0) {
			return;
		}

		final int cellW = getWidth() / cols;
		final int cellH = getHeight() / rows;
		final int x = e.getX() / cellW;
		final int y = e.getY() / cellH;

		if (x < 0 || y < 0 || x >= cols || y >= rows) {
			return;
		}

		blocked[y][x] = !blocked[y][x];

		orangePath = null;
		repaint();
	}

	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);

		if (cols == 0 || rows == 0) {
			return;
		}

		final Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final int cellW = getWidth() / cols;
		final int cellH = getHeight() / rows;

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {
				final int px = x * cellW;
				final int py = y * cellH;

				g2.setColor(blocked[y][x] ? Color.BLACK : Color.WHITE);
				g2.fillRect(px, py, cellW, cellH);
			}
		}

		if (orangePath != null) {
			for (final Point p : orangePath) {
				final int px = p.x * cellW;
				final int py = p.y * cellH;

				g2.setColor(ORANGE);
				g2.fillRect(px, py, cellW, cellH);
			}
		}

		g2.setColor(Color.BLACK);

		for (int x = 0; x <= cols; x++) {
			final int px = x * cellW;
			g2.drawLine(px, 0, px, rows * cellH);
		}

		for (int y = 0; y <= rows; y++) {
			final int py = y * cellH;
			g2.drawLine(0, py, cols * cellW, py);
		}

		if (orangePath != null && orangePath.size() > 1) {
			g2.setColor(Color.RED);
			g2.setStroke(new BasicStroke(2f));

			for (int i = 0; i < orangePath.size() - 1; i++) {
				final Point a = orangePath.get(i);
				final Point b = orangePath.get(i + 1);

				final int ax = a.x * cellW + cellW / 2;
				final int ay = a.y * cellH + cellH / 2;
				final int bx = b.x * cellW + cellW / 2;
				final int by = b.y * cellH + cellH / 2;

				g2.drawLine(ax, ay, bx, by);
			}
		}

		g2.dispose();
	}
}
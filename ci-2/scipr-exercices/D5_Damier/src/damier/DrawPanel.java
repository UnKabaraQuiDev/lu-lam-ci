package damier;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
	
	// public static final int COLUMN_SIZE = 50;
	// public static final int SIDE_SIZE = COLUMN_SIZE * Checkers.SIDE_COUNT;

	private Checkers checkers = new Checkers();

	private int columnSize = 50;
	private int sideSize = 50 * Checkers.SIDE_COUNT;

	@Override
	protected void paintComponent(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;

		sideSize = Math.min(getWidth(), getHeight());
		columnSize = sideSize / Checkers.SIDE_COUNT;
		sideSize = columnSize * Checkers.SIDE_COUNT;
		
		g.translate((getWidth() - sideSize) / 2, (getHeight() - sideSize) / 2);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, sideSize, sideSize);

		final int cols, rows = cols = Checkers.SIDE_COUNT;

		final double colWidth = (double) sideSize / cols;
		final double rowHeight = (double) sideSize / rows;

		g.setColor(Color.GRAY);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < cols; col++) {
				if ((row + col) % 2 == 0) {
					g.fillRect((int) (col * colWidth), (int) (row * rowHeight), (int) colWidth, (int) rowHeight);
				}
			}
		}

		g.setColor(Color.BLACK);
		for (int x = 0; x <= cols; x++) {
			g.drawLine((int) (x * ((double) sideSize / cols)), 0, (int) (x * ((double) sideSize / cols)), sideSize);
			g.drawLine(0, (int) (x * ((double) sideSize / rows)), sideSize, (int) (x * ((double) sideSize / rows)));
		}

		g.drawRect(1, 1, sideSize - 1, sideSize - 1);

		checkers.accept(this, g);
	}

	public int getColumnSize() {
		return columnSize;
	}
	
	public int getSideSize() {
		return sideSize;
	}

	public Checkers getCheckers() {
		return checkers;
	}

	public void setCheckers(Checkers checkers) {
		this.checkers = checkers;
	}

}

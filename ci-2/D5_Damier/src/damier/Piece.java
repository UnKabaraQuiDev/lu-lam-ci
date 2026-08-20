package damier;

import java.awt.Color;
import java.awt.Graphics2D;

public class Piece implements DrawPanelDrawable {

	private static final double SIZE_FACTOR = 0.9;

	private Color color;
	private int column, row;

	public Piece(Color color, int column, int row) {
		this.color = color;
		this.column = column;
		this.row = row;
	}

	@Override
	public void accept(DrawPanel panel, Graphics2D g) {
		g.setColor(color);
		int size = (int) ((double) panel.getColumnSize() * SIZE_FACTOR);
		g.fillOval((int) (column * panel.getColumnSize() + (1d - SIZE_FACTOR) / 2 * panel.getColumnSize()), (int) (row * panel.getColumnSize() + (1d - SIZE_FACTOR) / 2 * panel.getColumnSize()), size, size);
	}

	public void moveTo(int column, int row) {
		this.column = column;
		this.row = row;
	}

	public boolean isAt(int c, int r) {
		return column == c && row == r;
	}

	public Color getColor() {
		return color;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	@Override
	public String toString() {
		return "Piece [color=" + color + ", column=" + column + ", row=" + row + "]";
	}

}

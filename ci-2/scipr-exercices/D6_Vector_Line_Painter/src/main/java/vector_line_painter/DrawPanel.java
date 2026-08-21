package vector_line_painter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class DrawPanel extends JPanel implements Drawable {

	private static final long serialVersionUID = 1L;

	private List<Line> lines = new ArrayList<>();

	private Color color = Color.BLACK;

	/**
	 * Create the panel.
	 */
	public DrawPanel() {
		super.setBackground(Color.WHITE);
		super.setMinimumSize(new Dimension(500, 500));
		super.setPreferredSize(new Dimension(500, 500));
	}

	@Override
	public void accept(Graphics2D g2d) {
		g2d.setColor(super.getBackground());
		g2d.fillRect(0, 0, getWidth(), getHeight());

		if (lines != null)
			lines.forEach(l -> l.accept(g2d));

		g2d.dispose();
	}

	@Override
	protected void paintComponent(Graphics g) {
		this.accept((Graphics2D) g);
	}

	public void addLine(int x0, int y0, int x1, int y1) {
		lines.add(new Line(x0, y0, x1, y1, color));
		repaint();
	}
	
	public void clear() {
		lines.clear();
		repaint();
	}
	
	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<Line> getLines() {
		return lines;
	}

}

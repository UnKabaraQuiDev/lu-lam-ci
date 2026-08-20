import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private Lines lines;

	public DrawPanel() {
		lines = new Lines();
	}

	public DrawPanel(Lines lines) {
		this.lines = lines;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		lines.draw((Graphics2D) g);
	}

	public Lines getLines() {
		return lines;
	}

	public void setLines(Lines lines) {
		this.lines = lines;
	}

}

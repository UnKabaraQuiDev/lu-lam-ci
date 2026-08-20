import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private Shapes lines;

	public DrawPanel() {
		lines = new Shapes();
	}

	public DrawPanel(Shapes lines) {
		this.lines = lines;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		lines.draw((Graphics2D) g);
	}

	public Shapes getLines() {
		return lines;
	}

	public void setLines(Shapes lines) {
		this.lines = lines;
	}

}

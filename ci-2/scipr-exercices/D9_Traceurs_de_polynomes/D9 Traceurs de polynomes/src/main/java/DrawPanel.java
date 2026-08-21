import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private PlotFrame plotFrame;

	private Polynomial poly = new Polynomial();

	public DrawPanel(PlotFrame plotFrame) {
		this.plotFrame = plotFrame;
		super.setBackground(Color.WHITE);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(super.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(super.getForeground());
		g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
		g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
		
		if (poly != null)
			poly.draw(this, (Graphics2D) g);
		
		g.dispose();
	}

	public Polynomial getPoly() {
		return poly;
	}

	public void setPoly(Polynomial poly) {
		this.poly = poly;
	}

}

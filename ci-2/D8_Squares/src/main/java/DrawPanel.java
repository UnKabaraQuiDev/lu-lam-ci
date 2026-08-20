import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private MainFrame mainFrame;

	public DrawPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(super.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
	
		mainFrame.getSquares().draw((Graphics2D) g, this);
		
		g.dispose();
	}

}

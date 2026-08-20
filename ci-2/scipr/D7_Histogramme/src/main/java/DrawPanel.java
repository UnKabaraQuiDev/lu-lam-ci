import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private MainFrame mainFrame;

	public DrawPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		super.setBackground(Color.WHITE);
		super.setForeground(Color.BLACK);
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(super.getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
	
		mainFrame.getInfos().draw((Graphics2D) g, this);
		
		g.setColor(super.getForeground());
		g.drawString(mainFrame.getInfos().getTitle(), 0, g.getFontMetrics().getHeight());
		
		g.dispose();
	}

}

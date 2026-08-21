import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private AnalogWatch aw = new AnalogWatch();
	
	public DrawPanel() {
		super.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		aw.accept(this, (Graphics2D) g);
	}

}

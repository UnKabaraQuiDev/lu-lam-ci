import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MainFrame extends JFrame {

	private DrawPanel drawPanel;
	
	public MainFrame() {
		super.setSize(500, 300);
		super.setMinimumSize(new Dimension(250, 150));
		
		drawPanel = new DrawPanel();
		getContentPane().add(drawPanel);
		
		setVisible(true);
		
		new Timer(1000/5, (e) -> {
			drawPanel.repaint();
		}).start();
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}

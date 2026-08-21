import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.joml.Vector2d;

public class DrawPanel extends JPanel {

	private final int FPS = 120;

	private Timer timer;

	private Circle circle;

	public DrawPanel(MainFrame mainFrame) {
		circle = new Circle(new Vector2d(50, 50), new Vector2d(1, 2), 120, 50, Color.RED);

		timer = new Timer(1000 / FPS, e -> {
			circle.fixedUpdate((double) 1 / FPS, new Vector2d(getWidth(), getHeight()));
			repaint();
		});

		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		circle.draw((Graphics2D) g, this);
	}

}

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.joml.Vector2d;

public class DrawPanel extends JPanel {

	public static int FPS = 120;
	public static double SIMULATION_SPEED = 1;

	private Timer timer;

	private Balls balls;

	public DrawPanel(MainFrame mainFrame, Balls balls2) {
		this.balls = balls2;

		timer = new Timer(1000 / FPS, e -> {
			balls.fixedUpdate((double) 1 / FPS * SIMULATION_SPEED, new Vector2d(getWidth(), getHeight()));
			repaint();
		});

		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		((Graphics2D) g).setStroke(new BasicStroke(2));

		super.paintComponent(g);
		balls.drawBalls((Graphics2D) g, new Vector2d(getWidth(), getHeight()));

		// balls.drawLine((Graphics2D) g);
	}

	public Balls getBalls() {
		return balls;
	}

}

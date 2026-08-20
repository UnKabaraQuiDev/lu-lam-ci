
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class MainFrame extends JFrame {
	private Game game;
	private DrawPanel panel;
	private Timer timer;

	public MainFrame() {
		setTitle("Space Invaders");
		setMinimumSize(new Dimension(500, 530));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		panel = new DrawPanel();
		add(panel);

		pack();
		setVisible(true);
		
		game = new Game(500, 500);
		panel.setGame(game);
		
		timer = new Timer(1000 / 60, e -> {
			game.move();
			if (game.isOver())
				game = new Game(500, 500);
			panel.repaint();
		});
		timer.start();

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				game.setPlayerX(e.getX());
				panel.repaint();
			}
			@Override
			public void mouseDragged(MouseEvent e) {
				game.setPlayerX(e.getX());
				panel.repaint();
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1)
					game.launchMissile();
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
	}
}

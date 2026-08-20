
import java.awt.event.MouseEvent;

import javax.swing.Timer;

public class MainFrame extends javax.swing.JFrame {

	public Game game = null;
	public Timer timer = null;

	private int pressedMouseButton = 0;

	public MainFrame() {
		initComponents();
		game = new Game(drawPanel.getWidth(), drawPanel.getHeight());
		drawPanel.setGame(game);
		timer = new Timer(10, event -> {
			if (game.move() == 1) {
				game.playerBallReset();
				updateView();
				timer.stop();
			}
			if (game.isOver()) {
				resetGame();
			}
			updateView();
		});
		setTitle("Angry Balls");
	}

	public void updateView() {
		drawPanel.repaint();
	}

	public void resetGame() {
		game = new Game(drawPanel.getWidth(), drawPanel.getHeight());
		drawPanel.setGame(game);
		updateView();
		timer.stop();
	}

	private void initComponents() {

		drawPanel = new DrawPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		drawPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
			public void mouseDragged(java.awt.event.MouseEvent evt) {
				drawPanelMouseDragged(evt);
			}
		});
		drawPanel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				drawPanelMouseClicked(evt);
			}

			public void mousePressed(java.awt.event.MouseEvent evt) {
				drawPanelMousePressed(evt);
			}

			public void mouseReleased(java.awt.event.MouseEvent evt) {
				drawPanelMouseReleased(evt);
			}
		});

		javax.swing.GroupLayout drawPanelLayout = new javax.swing.GroupLayout(drawPanel);
		drawPanel.setLayout(drawPanelLayout);
		drawPanelLayout.setHorizontalGroup(drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		drawPanelLayout.setVerticalGroup(drawPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(drawPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	private void drawPanelMousePressed(java.awt.event.MouseEvent evt) {
		pressedMouseButton = evt.getButton();
		if (pressedMouseButton == MouseEvent.BUTTON1 && !timer.isRunning()) {
			game.setMousePosition(evt.getPoint());
			updateView();
		}
	}

	private void drawPanelMouseDragged(java.awt.event.MouseEvent evt) {
		if (pressedMouseButton == MouseEvent.BUTTON1 && !timer.isRunning()) {
			game.setMousePosition(evt.getPoint());
			updateView();
		}
	}

	private void drawPanelMouseReleased(java.awt.event.MouseEvent evt) {
		if (pressedMouseButton == MouseEvent.BUTTON1 && !timer.isRunning()) {
			game.setPlayerBallSteps();
			game.setMousePosition(null);
			timer.start();
		}
	}

	private void drawPanelMouseClicked(java.awt.event.MouseEvent evt) {
		pressedMouseButton = evt.getButton();
		if (pressedMouseButton == MouseEvent.BUTTON3) {
			resetGame();
		}
	}

	public static void main(String args[]) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	private DrawPanel drawPanel;

}

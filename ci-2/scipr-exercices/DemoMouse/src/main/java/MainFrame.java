import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private Graphics g;

	private int lastX = -1, lastY = -1;
	private boolean button1Pressed = false;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() != MouseEvent.BUTTON1) {
					return;
				}
				
				button1Pressed = false;
				
				g.setColor(Color.RED);
				g.fillOval(e.getX(), e.getY(), 10, 10);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				lastX = -1;
				lastY = -1;

				if (e.getButton() != MouseEvent.BUTTON1) {
					return;
				}
				
				button1Pressed = false;
				
				g.setColor(Color.GREEN);
				g.fillOval(e.getX(), e.getY(), 10, 10);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (lastX == -1 || lastY == -1) {
					lastX = e.getX();
					lastY = e.getY();

					return;
				}

				if (button1Pressed) {
					return;
				}
				
				g.setColor(Color.BLUE);

				g.drawLine(lastX, lastY, e.getX(), e.getY());

				lastX = e.getX();
				lastY = e.getY();
				
				g.setColor(Color.BLACK);
				g.drawRect(lastX, lastY, 100, 50);
			}
		});

		setVisible(true);

		g = getGraphics();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

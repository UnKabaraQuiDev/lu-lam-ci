import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class RandomPointsTriangle extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;

	private int iterations = 10000;

	/**
	 * Create the frame.
	 */
	public RandomPointsTriangle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		final JPanel drawPanel = new JPanel() {
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				int size = Math.min(getWidth(), getHeight()) - 40;
				int x1 = getWidth() / 2;
				int y1 = 20;
				int x2 = 20;
				int y2 = getHeight() - 20;
				int x3 = getWidth() - 20;
				int y3 = getHeight() - 20;

				// Draw the triangle outline
				g2d.setColor(Color.BLACK);
				g2d.setStroke(new BasicStroke(2));
				g2d.drawPolygon(new int[] { x1, x2, x3 }, new int[] { y1, y2, y3 }, 3);

				Random rand = new Random();

				Point p = randomPointInTriangle(x1, y1, x2, y2, x3, y3, rand);

				g2d.setStroke(new BasicStroke(5f));

				g2d.setColor(Color.BLUE);
				for (int i = 0; i < iterations; i++) {
					int choice = rand.nextInt(3);
					int vx, vy;
					if (choice == 0) {
						vx = x1;
						vy = y1;
					} else if (choice == 1) {
						vx = x2;
						vy = y2;
					} else {
						vx = x3;
						vy = y3;
					}

					double t = rand.nextDouble();
					final int newX = (vx + p.x) / 2;
					final int newY = (vy + p.y) / 2;

					g2d.drawLine(newX, newY, newX, newY);

					p = new Point(newX, newY);
				}
			}

			private Point randomPointInTriangle(int x1, int y1, int x2, int y2, int x3, int y3, Random rand) {
				double r1 = Math.sqrt(rand.nextDouble());
				double r2 = rand.nextDouble();
				double x = (1 - r1) * x1 + (r1 * (1 - r2)) * x2 + (r1 * r2) * x3;
				double y = (1 - r1) * y1 + (r1 * (1 - r2)) * y2 + (r1 * r2) * y3;
				return new Point((int) x, (int) y);
			}

		};

		contentPane.add(drawPanel, BorderLayout.CENTER);
		setContentPane(contentPane);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RandomPointsTriangle frame = new RandomPointsTriangle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

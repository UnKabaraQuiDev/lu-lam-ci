import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SierpinskiTriangle extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;

	private int depthCount = 5;

	/**
	 * Create the frame.
	 */
	public SierpinskiTriangle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		final JPanel drawPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				final Graphics2D g2 = (Graphics2D) g;

				int size = Math.min(getWidth(), getHeight());
				int x1 = (getWidth() - size) / 2;
				int y1 = getHeight() - (getHeight() - size) / 2;
				int x2 = x1 + size;
				int y2 = y1;
				int x3 = x1 + size / 2;
				int y3 = y1 - (int) (Math.sqrt(3) * size / 2);

				drawSierpinski(g2, depthCount, x1, y1, x2, y2, x3, y3);
			}

			private void drawSierpinski(Graphics2D g, int depth, int x1, int y1, int x2, int y2, int x3, int y3) {
				if (depth == 0) {
					int[] xPoints = { x1, x2, x3 };
					int[] yPoints = { y1, y2, y3 };
					g.fillPolygon(xPoints, yPoints, 3);
				} else {
					int midX12 = (x1 + x2) / 2;
					int midY12 = (y1 + y2) / 2;
					int midX23 = (x2 + x3) / 2;
					int midY23 = (y2 + y3) / 2;
					int midX31 = (x3 + x1) / 2;
					int midY31 = (y3 + y1) / 2;

					drawSierpinski(g, depth - 1, x1, y1, midX12, midY12, midX31, midY31);
					drawSierpinski(g, depth - 1, midX12, midY12, x2, y2, midX23, midY23);
					drawSierpinski(g, depth - 1, midX31, midY31, midX23, midY23, x3, y3);
				}
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
					SierpinskiTriangle frame = new SierpinskiTriangle();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SierpinskiCurve extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;

	private int depthCount = 6;

	/**
	 * Create the frame.
	 */
	public SierpinskiCurve() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		final JPanel drawPanel = new JPanel() {
			private List<Line2D.Double> currentSegments = new ArrayList<>();

			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				final Graphics2D g2d = (Graphics2D) g;
				
				g2d.setStroke(new BasicStroke(5));

				int size = Math.min(getWidth(), getHeight());
				int x1 = (getWidth() - size) / 2;
				int y1 = getHeight() - (getHeight() - size) / 2 -20;
				int x2 = x1 + size;
				int y2 = y1;
				int x3 = x1 + size / 2;
				int y3 = y1 - (int) (Math.sqrt(3) * size / 2);

				currentSegments.clear();
				genSegments(new Line2D.Double(x1, y1, x2, y2), depthCount);

				currentSegments.forEach(g2d::draw);
			}

			private void genSegments(Line2D.Double line, int iteration) {
				if (iteration == 0) {
					currentSegments.add(line);
					return;
				}

				// @formatter:off
				// p1                  p4
				// p1 ^p2^        ^p3^ p4
				// @formatter:on

				double x1 = line.x1;
				double y1 = line.y1;
				double x4 = line.x2;
				double y4 = line.y2;

				double dx = (x4 - x1) / 2.0;
				double dy = (y4 - y1) / 2.0;

				double angle = Math.atan2(y4 - y1, x4 - x1) - Math.PI / 3.0; // angle of the current g=segment+ angle of difference to turn
																				// the inner point yk
				double len = Math.hypot(dx, dy);

				double xPeak1, xPeak2, yPeak1, yPeak2;

				xPeak1 = x1 + Math.cos(angle) * len;
				yPeak1 = y1 + Math.sin(angle) * len;

				xPeak2 = xPeak1 + dx;
				yPeak2 = yPeak1 + dy;

				genSegments(new Line2D.Double(xPeak1, yPeak1, x1, y1), iteration - 1);
				genSegments(new Line2D.Double(xPeak1, yPeak1, xPeak2, yPeak2), iteration - 1);
				genSegments(new Line2D.Double(x4, y4, xPeak2, yPeak2), iteration - 1);
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
					SierpinskiCurve frame = new SierpinskiCurve();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

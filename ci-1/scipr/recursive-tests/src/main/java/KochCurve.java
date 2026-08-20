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

public class KochCurve extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;

	private int depthCount = 5;

	/**
	 * Create the frame.
	 */
	public KochCurve() {
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

				currentSegments.clear();
				genSegments(new Line2D.Double(50, getHeight() / 2.0, getWidth() - 50, getHeight() / 2.0), depthCount);

				currentSegments.forEach(g2d::draw);
			}

			private void genSegments(Line2D.Double line, int iteration) {
				if (iteration == 0) {
					currentSegments.add(line);
					return;
				}

				// @formatter:off
				// p1                  p5
				// p1  p2  ^ p3 ^  p4  p5
				// @formatter:on

				double x1 = line.x1;
				double y1 = line.y1;
				double x5 = line.x2;
				double y5 = line.y2;

				double dx = (x5 - x1) / 3.0;
				double dy = (y5 - y1) / 3.0;

				double x2 = x1 + dx;
				double y2 = y1 + dy;
				double x3 = x1 + 2 * dx;
				double y3 = y1 + 2 * dy;

				double angle = Math.atan2(y5 - y1, x5 - x1) - Math.PI / 3.0; // angle of the current g=segment+ angle of difference to turn
																				// the inner point yk
				double xPeak = x2 + Math.cos(angle) * Math.hypot(dx, dy);
				double yPeak = y2 + Math.sin(angle) * Math.hypot(dx, dy);

				genSegments(new Line2D.Double(x1, y1, x2, y2), iteration - 1);
				genSegments(new Line2D.Double(x2, y2, xPeak, yPeak), iteration - 1);
				genSegments(new Line2D.Double(xPeak, yPeak, x3, y3), iteration - 1);
				genSegments(new Line2D.Double(x3, y3, x5, y5), iteration - 1);
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
					KochCurve frame = new KochCurve();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

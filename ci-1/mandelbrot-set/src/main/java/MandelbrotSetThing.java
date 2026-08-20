import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MandelbrotSetThing extends JFrame {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPane;

	class MandelbrotPanel extends JPanel {
		private double zoom = 200;
		private double moveX = -0.5;
		private double moveY = 0;
		private int maxIter = 100;

		private BufferedImage image;

		private int lastX, lastY;
		private final float renderScale = 0.5f;

		private volatile boolean working = false;

		public MandelbrotPanel() {
			generateImage();

			addMouseWheelListener(e -> {
				double factor = e.getPreciseWheelRotation() > 0 ? 0.9 : 1.1;
				zoom *= factor;
				System.err.println("zoom: " + zoom);
				generateImage();
			});

			addMouseMotionListener(new MouseAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					int dx = e.getX() - lastX;
					int dy = e.getY() - lastY;
					moveX -= dx / zoom * renderScale;
					moveY -= dy / zoom * renderScale;
					lastX = e.getX();
					lastY = e.getY();
					System.err.println("move: " + moveX + "x" + moveY);
					generateImage();
				}
			});

			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					lastX = e.getX();
					lastY = e.getY();
				}
			});
		}

		private void generateImage() {
			synchronized (this) {
				if (working) {
					System.err.println("Already working, stopping.");
					return;
				}
				working = true;
			}

			try {
				maxIter = (int) (Math.log(zoom)) * 10;
				System.err.println("Max iter: " + maxIter);

				System.err.println("generating");

				int width = (int) (getWidth() * renderScale);
				int height = (int) (getHeight() * renderScale);
				System.err.println(width + " " + height);
				if (width <= 0 || height <= 0) {
					System.err.println("Invalid size, skipping.");
					return;
				}

				image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						double zx = 0;
						double zy = 0;
						double cX = (x - width / 2.0) / zoom + moveX;
						double cY = (y - height / 2.0) / zoom + moveY;
						int iter = 0;

						while (zx * zx + zy * zy < 4 && iter < maxIter) {
							double tmp = zx * zx - zy * zy + cX;
							zy = 2.0 * zx * zy + cY;
							zx = tmp;
							iter++;
						}

						int color = iter == maxIter ? 0 : Color.HSBtoRGB((float) iter / maxIter, 1, 1);
						image.setRGB(x, y, color);
					}
				}

				System.err.println("generated");
			} finally {
				synchronized (this) {
					working = false;
				}
			}

			repaint();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			if (image == null) {
				generateImage();
			}
			if (image != null) {
				g.drawImage(image, 0, 0, getWidth(), getHeight(), 0, 0, image.getWidth(), image.getHeight(), null);
			}
		}
	}

	/**
	 * Create the frame.
	 */
	public MandelbrotSetThing() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		final JPanel drawPanel = new MandelbrotPanel();

		contentPane.add(drawPanel, BorderLayout.CENTER);
		setContentPane(contentPane);

		drawPanel.grabFocus();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MandelbrotSetThing frame = new MandelbrotSetThing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

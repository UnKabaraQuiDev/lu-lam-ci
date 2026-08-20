import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.Timer;

import lu.pcy113.pclib.PCUtils;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private DrawPanel drawPanel;

	private int pressedBtn;
	private CustomShape currentShape;

	private Shapes shapes = new Shapes();
	private Color color = Color.BLACK;

	private JList list;

	private JButton addSingleLineBtn;
	private JButton timerBtn;
	private Timer timer;

	public MainFrame() {
		timer = new Timer(10, this::addSingleLine);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		getContentPane().setLayout(new BorderLayout());

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BorderLayout());

		list = new JList();

		sidePanel.add(list);

		JButton colorBtn = new JButton("Change color");

		colorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorDialog();
			}
		});

		sidePanel.add(colorBtn, BorderLayout.SOUTH);

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

		addSingleLineBtn = new JButton("Draw single line");
		addSingleLineBtn.addActionListener(this::addSingleLine);

		timerBtn = new JButton("Start timer");
		timerBtn.addActionListener(this::toggleTimer);

		topPanel.add(addSingleLineBtn);
		topPanel.add(timerBtn);

		getContentPane().add(topPanel, BorderLayout.NORTH);

		drawPanel = new DrawPanel(shapes);
		getContentPane().add(drawPanel, BorderLayout.CENTER);

		drawPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				drawPanelMousePressed(e);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				drawPanelMouseReleased(e);
			}
		});

		drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				drawPanelMouseDragged(e);
			}
		});

		getContentPane().add(sidePanel, BorderLayout.EAST);

		setVisible(true);
	}

	protected void colorDialog() {
		color = JColorChooser.showDialog(this, "Choix d'une couleur", color);
	}

	private void drawPanelMousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			currentShape = new Line(color, e.getPoint(), e.getPoint());
			shapes.add(currentShape);
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			currentShape = new Rectangle(color, e.getPoint(), e.getPoint());
			shapes.add(currentShape);
		}

		list.setListData(shapes.getShapes().toArray());
	}

	private void drawPanelMouseReleased(MouseEvent e) {
		currentShape.setEndpoint(e.getPoint());

		drawPanel.repaint();
		list.setListData(shapes.getShapes().toArray());
	}

	private void drawPanelMouseDragged(MouseEvent e) {
		currentShape.setEndpoint(e.getPoint());

		drawPanel.repaint();
		list.setListData(shapes.getShapes().toArray());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void toggleTimer(ActionEvent e) {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}

		timerBtn.setText(timer.isRunning() ? "Stop timer" : "Start timer");
	}

	private void addSingleLine(ActionEvent e) {
		shapes.add(new Line(color, new Point(PCUtils.randomIntRange(0, drawPanel.getWidth()), PCUtils.randomIntRange(0, drawPanel.getHeight())),
				new Point(PCUtils.randomIntRange(0, drawPanel.getWidth()), PCUtils.randomIntRange(0, drawPanel.getHeight()))));
		drawPanel.repaint();
		list.setListData(shapes.getShapes().toArray());
	}

}

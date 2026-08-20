import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import lu.pcy113.pclib.PCUtils;

public class MainFrame extends JFrame {

	private Squares squares = new Squares();

	private JPanel contentPane;
	private JTextField yTf;
	private JTextField sideTf;
	private JTextField xTf;
	private JList list;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 228, 92));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);

		JLabel xLbl = new JLabel("X=");
		panel_2.add(xLbl);

		xTf = new JTextField();
		panel_2.add(xTf);
		xTf.setColumns(10);

		JButton add100SquaresBtn = new JButton("Add 100 Squares");
		panel_2.add(add100SquaresBtn);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		JLabel yLbl = new JLabel("Y=");
		panel_3.add(yLbl);

		yTf = new JTextField();
		panel_3.add(yTf);
		yTf.setColumns(10);

		JButton clearBtn = new JButton("Clear");
		panel_3.add(clearBtn);
		clearBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				squares.clear();
				
				repaint();
			}
		});

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);

		JLabel sideLbl = new JLabel("Side=");
		panel_4.add(sideLbl);

		sideTf = new JTextField();
		panel_4.add(sideTf);
		sideTf.setColumns(10);

		JButton addSquareBtn = new JButton("Create Square");
		panel_4.add(addSquareBtn);
		addSquareBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				squares.add(new Square(Integer.parseInt(xTf.getText()), Integer.parseInt(yTf.getText()), Integer.parseInt(sideTf.getText())));
				
				repaint();
			}
		});

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		DrawPanel drawPanel = new DrawPanel(this);
		panel_1.add(drawPanel, BorderLayout.CENTER);
		
		add100SquaresBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for(int i = 0; i < 100; i++) {
					squares.add(new Square(PCUtils.randomIntRange(0, drawPanel.getWidth()), PCUtils.randomIntRange(0, drawPanel.getHeight()), PCUtils.randomIntRange(10, 50)));
				}
				
				repaint();
			}
		});
	}

	public Squares getSquares() {
		return squares;
	}

	public void setSquares(Squares squares) {
		this.squares = squares;
	}

	public JTextField getLabelTf() {
		return yTf;
	}

	public JTextField getValueTf() {
		return sideTf;
	}

	public JTextField getTitleTf() {
		return xTf;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

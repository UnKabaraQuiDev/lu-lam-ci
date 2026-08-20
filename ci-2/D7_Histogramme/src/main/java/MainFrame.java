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

public class MainFrame extends JFrame {

	private NumberInfos infos = new NumberInfos();

	private JPanel contentPane;
	private JTextField labelTf;
	private JTextField valueTf;
	private JTextField titleTf;
	private JList list;

	private void test() {
		infos = new NumberInfos("Precipitation (in mm)");
		
		// pour la démonstation
		infos.add(63, "JAN");
		infos.add(65, "FEB");
		infos.add(58, "MAR");
		infos.add(77, "APR");
		infos.add(111, "MAY");
		infos.add(124, "JUN");
		infos.add(104, "JUL");
		infos.add(99, "AUG");
		infos.add(80, "SEP");
		infos.add(66, "OCT");
		infos.add(103, "NOV");
		infos.add(80, "DEC");
	}

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

		JLabel titleLbl = new JLabel("Title:");
		panel_2.add(titleLbl);

		titleTf = new JTextField();
		panel_2.add(titleTf);
		titleTf.setColumns(10);

		JButton newBtn = new JButton("New");
		newBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				infos = new NumberInfos(titleTf.getText());

				infos.update(list);
				repaint();
			}
		});
		panel_2.add(newBtn);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		JLabel labelLbl = new JLabel("Label:");
		panel_3.add(labelLbl);

		labelTf = new JTextField();
		panel_3.add(labelTf);
		labelTf.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);

		JLabel valueLbl = new JLabel("Value");
		panel_4.add(valueLbl);

		valueTf = new JTextField();
		panel_4.add(valueTf);
		valueTf.setColumns(10);

		JButton btnNewButton = new JButton("Add");
		panel_4.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				infos.add(Double.parseDouble(valueTf.getText()), labelTf.getText());

				infos.update(list);
				repaint();
			}
		});

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel panel_5 = new JPanel();
		panel_1.add(panel_5, BorderLayout.EAST);

		list = new JList();
		panel_5.add(list);

		DrawPanel drawPanel = new DrawPanel(this);
		panel_1.add(drawPanel, BorderLayout.CENTER);

		test();
		infos.update(list);
	}

	public NumberInfos getInfos() {
		return infos;
	}

	public void setInfos(NumberInfos infos) {
		this.infos = infos;
	}

	public JTextField getLabelTf() {
		return labelTf;
	}

	public JTextField getValueTf() {
		return valueTf;
	}

	public JTextField getTitleTf() {
		return titleTf;
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

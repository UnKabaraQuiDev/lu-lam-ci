package vector_line_painter;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JSpinner p1y;
	private JSpinner p1x;
	private JSpinner p0x;
	private JSpinner p0y;
	private DrawPanel drawPanel;
	private JColorChooser colorChooser;

	private void addLine(ActionEvent actionevent1) {
		drawPanel.addLine((int) p0x.getValue(), (int) p0y.getValue(), (int) p1x.getValue(), (int) p1y.getValue());
	}
	
	private void changeColor(ChangeEvent evt) {
		drawPanel.setColor(colorChooser.getColor());
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
		contentPane.add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		panel.setLayout(gbl_panel);

		JLabel newLineLbl = new JLabel("New line from");
		GridBagConstraints gbc_newLineLbl = new GridBagConstraints();
		gbc_newLineLbl.fill = GridBagConstraints.BOTH;
		gbc_newLineLbl.insets = new Insets(0, 0, 5, 5);
		gbc_newLineLbl.gridx = 0;
		gbc_newLineLbl.gridy = 0;
		panel.add(newLineLbl, gbc_newLineLbl);

		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.weightx = 1.0;
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 48, 48, 0 };
		gbl_panel_1.rowHeights = new int[] { 20, 20, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JLabel lblNewLabel = new JLabel("X=");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);

		p0x = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.BOTH;
		gbc_spinner.insets = new Insets(0, 0, 5, 0);
		gbc_spinner.gridx = 1;
		gbc_spinner.gridy = 0;
		panel_1.add(p0x, gbc_spinner);

		JLabel lblNewLabel_1 = new JLabel("Y=");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);

		p0y = new JSpinner();
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.weightx = 1.0;
		gbc_spinner_1.fill = GridBagConstraints.BOTH;
		gbc_spinner_1.gridx = 1;
		gbc_spinner_1.gridy = 1;
		panel_1.add(p0y, gbc_spinner_1);

		JLabel lblNewLabel_4 = new JLabel("to");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 2;
		gbc_lblNewLabel_4.gridy = 0;
		panel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.weightx = 1.0;
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.gridx = 3;
		gbc_panel_2.gridy = 0;
		panel.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 48, 48, 0 };
		gbl_panel_2.rowHeights = new int[] { 20, 20, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		JLabel lblNewLabel_2 = new JLabel("X=");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);

		p1x = new JSpinner();
		GridBagConstraints gbc_spinner_2 = new GridBagConstraints();
		gbc_spinner_2.weightx = 1.0;
		gbc_spinner_2.fill = GridBagConstraints.BOTH;
		gbc_spinner_2.gridx = 1;
		gbc_spinner_2.gridy = 0;
		panel_2.add(p1x, gbc_spinner_2);

		JLabel lblNewLabel_3 = new JLabel("Y=");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);

		p1y = new JSpinner();
		GridBagConstraints gbc_spinner_3 = new GridBagConstraints();
		gbc_spinner_3.weightx = 1.0;
		gbc_spinner_3.fill = GridBagConstraints.BOTH;
		gbc_spinner_3.gridx = 1;
		gbc_spinner_3.gridy = 1;
		panel_2.add(p1y, gbc_spinner_3);

		JButton btnNewButton = new JButton("Add");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.fill = GridBagConstraints.BOTH;
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 0;
		panel.add(btnNewButton, gbc_btnNewButton);

		drawPanel = new DrawPanel();
		contentPane.add(drawPanel, BorderLayout.CENTER);
		
		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(this::changeColor);
		colorChooser.setBorder(BorderFactory.createTitledBorder("Choose Line Color"));
		contentPane.add(colorChooser, BorderLayout.SOUTH);

		btnNewButton.addActionListener(this::addLine);
		
		super.pack();
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

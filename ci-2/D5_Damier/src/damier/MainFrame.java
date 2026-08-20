package damier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;

import lu.pcy113.pclib.datastructure.pair.Pair;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	private DrawPanel drawPanel;

	private JLabel outputLabel;

	private JSpinner destRow;
	private JSpinner destCol;
	private JSpinner startCol;
	private JSpinner startRow;

	protected void movePiece() {
		try {
			final Pair<Boolean, String> msg = drawPanel.getCheckers().move(Integer.parseInt(startCol.getValue().toString()), Integer.parseInt(startRow.getValue().toString()),
					Integer.parseInt(destCol.getValue().toString()), Integer.parseInt(destRow.getValue().toString()));
			outputLabel.setText(msg.getValue());
			outputLabel.setForeground(msg.getKey() ? Color.GREEN : Color.RED);
		} catch (NumberFormatException e) {
			outputLabel.setText(e.getMessage());
			outputLabel.setForeground(Color.RED);
		}

		super.repaint();
	}

	protected void newGame() {
		drawPanel.setCheckers(new Checkers());
		repaint();
	}

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());

		drawPanel = new DrawPanel();
		contentPane.add(drawPanel, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel startLbl = new JLabel("Start");
		GridBagConstraints gbc_startLbl = new GridBagConstraints();
		gbc_startLbl.insets = new Insets(0, 0, 5, 5);
		gbc_startLbl.gridx = 0;
		gbc_startLbl.gridy = 0;
		panel.add(startLbl, gbc_startLbl);

		JLabel lblCol = new JLabel("Col:");
		GridBagConstraints gbc_lblCol = new GridBagConstraints();
		gbc_lblCol.insets = new Insets(0, 0, 5, 5);
		gbc_lblCol.gridx = 1;
		gbc_lblCol.gridy = 0;
		panel.add(lblCol, gbc_lblCol);

		startCol = new JSpinner();
		// startCol.setModel(getSpinnerModel());
		GridBagConstraints gbc_startCol = new GridBagConstraints();
		gbc_startCol.insets = new Insets(0, 0, 5, 5);
		gbc_startCol.gridx = 2;
		gbc_startCol.gridy = 0;
		panel.add(startCol, gbc_startCol);

		JLabel lblRow = new JLabel("Row:");
		// startCol.setModel(getSpinnerModel());
		GridBagConstraints gbc_lblRow = new GridBagConstraints();
		gbc_lblRow.insets = new Insets(0, 0, 5, 5);
		gbc_lblRow.gridx = 3;
		gbc_lblRow.gridy = 0;
		panel.add(lblRow, gbc_lblRow);

		startRow = new JSpinner();
		GridBagConstraints gbc_startRow = new GridBagConstraints();
		gbc_startRow.insets = new Insets(0, 0, 5, 5);
		gbc_startRow.gridx = 4;
		gbc_startRow.gridy = 0;
		panel.add(startRow, gbc_startRow);

		JButton btnMove = new JButton("Move");
		GridBagConstraints gbc_btnMove = new GridBagConstraints();
		gbc_btnMove.insets = new Insets(0, 0, 5, 5);
		gbc_btnMove.gridx = 5;
		gbc_btnMove.gridy = 0;
		panel.add(btnMove, gbc_btnMove);

		JButton btnNewGame = new JButton("New Game");
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewGame.gridx = 6;
		gbc_btnNewGame.gridy = 0;
		panel.add(btnNewGame, gbc_btnNewGame);

		JLabel destLbl = new JLabel("Destination");
		GridBagConstraints gbc_destLbl = new GridBagConstraints();
		gbc_destLbl.insets = new Insets(0, 0, 0, 5);
		gbc_destLbl.gridx = 0;
		gbc_destLbl.gridy = 1;
		panel.add(destLbl, gbc_destLbl);

		JLabel lblCol_1 = new JLabel("Col:");
		// startCol.setModel(getSpinnerModel());
		GridBagConstraints gbc_lblCol_1 = new GridBagConstraints();
		gbc_lblCol_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblCol_1.gridx = 1;
		gbc_lblCol_1.gridy = 1;
		panel.add(lblCol_1, gbc_lblCol_1);

		destCol = new JSpinner();
		GridBagConstraints gbc_destCol = new GridBagConstraints();
		gbc_destCol.insets = new Insets(0, 0, 0, 5);
		gbc_destCol.gridx = 2;
		gbc_destCol.gridy = 1;
		panel.add(destCol, gbc_destCol);

		JLabel lblRow_1 = new JLabel("Row:");
		// startCol.setModel(getSpinnerModel());
		GridBagConstraints gbc_lblRow_1 = new GridBagConstraints();
		gbc_lblRow_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblRow_1.gridx = 3;
		gbc_lblRow_1.gridy = 1;
		panel.add(lblRow_1, gbc_lblRow_1);

		destRow = new JSpinner();
		GridBagConstraints gbc_destRow = new GridBagConstraints();
		gbc_destRow.insets = new Insets(0, 0, 0, 5);
		gbc_destRow.gridx = 4;
		gbc_destRow.gridy = 1;
		panel.add(destRow, gbc_destRow);

		outputLabel = new JLabel("[ ... ... ]");
		GridBagConstraints gbc_outputLabel = new GridBagConstraints();
		gbc_outputLabel.anchor = GridBagConstraints.WEST;
		gbc_outputLabel.gridwidth = 2;
		gbc_outputLabel.insets = new Insets(0, 0, 0, 5);
		gbc_outputLabel.gridx = 5;
		gbc_outputLabel.gridy = 1;
		panel.add(outputLabel, gbc_outputLabel);

		repaint();

		// user code
		btnNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});

		btnMove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				movePiece();
			}
		});
	}

	/*private SpinnerModel getSpinnerModel() {
		return new SpinnerNumberModel(0, 0, Checkers.SIDE_COUNT, 1);
	}*/

	public JSpinner getDestRow() {
		return destRow;
	}

	public JSpinner getDestCol() {
		return destCol;
	}

	public JSpinner getStartRow() {
		return startRow;
	}

	public JSpinner getStartCol() {
		return startCol;
	}

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

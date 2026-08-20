import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private final JTextField colsField = new JTextField("7", 5);
	private final JTextField rowsField = new JTextField("5", 5);
	private final JTextField percentField = new JTextField("30", 5);

	private final JButton buildButton = new JButton("Build");
	private final JButton fillButton = new JButton("Fill");
	private final JButton solveButton = new JButton("Solve");

	private final JList<String> pathList = new JList<>();

	private final MazePanel mazePanel = new MazePanel();

	private final List<SolvedPath> solvedPaths = new ArrayList<>();

	public MainFrame() {
		super("Mazerunner");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new BorderLayout(8, 8));
		((JComponent) getContentPane()).setBorder(new EmptyBorder(8, 8, 8, 8));

		final JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));

		controlPanel.add(new JLabel("Cols"));
		controlPanel.add(colsField);
		controlPanel.add(Box.createVerticalStrut(8));
		controlPanel.add(new JLabel("Rows"));
		controlPanel.add(rowsField);
		controlPanel.add(Box.createVerticalStrut(8));
		controlPanel.add(buildButton);
		controlPanel.add(Box.createVerticalStrut(18));
		controlPanel.add(new JLabel("%"));
		controlPanel.add(percentField);
		controlPanel.add(Box.createVerticalStrut(8));
		controlPanel.add(fillButton);
		controlPanel.add(Box.createVerticalStrut(18));
		controlPanel.add(solveButton);
		controlPanel.add(Box.createVerticalGlue());

		final JPanel topPanel = new JPanel(new BorderLayout(8, 8));
		topPanel.add(mazePanel, BorderLayout.CENTER);
		topPanel.add(controlPanel, BorderLayout.EAST);

		pathList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pathList.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		final JScrollPane listScroll = new JScrollPane(pathList);
		listScroll.setPreferredSize(new Dimension(700, 110));

		add(topPanel, BorderLayout.CENTER);
		add(listScroll, BorderLayout.SOUTH);

		buildButton.addActionListener(e -> buildGrid());
		fillButton.addActionListener(e -> fillGrid());
		solveButton.addActionListener(e -> solveMaze());

		pathList.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				final int idx = pathList.getSelectedIndex();
				mazePanel.setOrangePath((idx >= 0 && idx < solvedPaths.size()) ? solvedPaths.get(idx) : null);
			}
		});

		buildGrid();

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void buildGrid() {
		try {
			final int cols = Integer.parseInt(colsField.getText().trim());
			final int rows = Integer.parseInt(rowsField.getText().trim());

			if (cols <= 0 || rows <= 0) {
				throw new NumberFormatException();
			}
			if (cols > 30 || rows > 30) {
				JOptionPane.showMessageDialog(this, "Please use values between 1 and 30.", "Invalid size", JOptionPane.WARNING_MESSAGE);
				return;
			}

			mazePanel.setGridSize(cols, rows);
			clearSolutions();

		} catch (final NumberFormatException ex) {
			throw ex;
		}
	}

	private void fillGrid() {
		try {
			final int pct = Integer.parseInt(percentField.getText().trim());
			if (pct < 0 || pct > 100) {
				throw new NumberFormatException();
			}

			mazePanel.randomFill(pct);
			clearSolutions();

		} catch (final NumberFormatException ex) {
			throw ex;
		}
	}

	private void solveMaze() {
		clearSolutions();

		final int cols = mazePanel.getCols();
		final int rows = mazePanel.getRows();

		if (cols == 0 || rows == 0) {
			return;
		}

		final boolean[][] visited = new boolean[rows][cols];
		final List<Point> currentPath = new ArrayList<>();

		searchAllPaths(0, 0, cols - 1, rows - 1, visited, currentPath, solvedPaths);

		Collections.sort(solvedPaths);

		System.err.println(solvedPaths);
		pathList.setListData(solvedPaths.stream().map(SolvedPath::toString).toArray(String[]::new));
//		pathList.repaint();

		if (!solvedPaths.isEmpty()) {
			pathList.setSelectedIndex(0);
		} else {
			mazePanel.setOrangePath(null);
		}
	}

	private void searchAllPaths(
			final int x,
			final int y,
			final int targetX,
			final int targetY,
			final boolean[][] visited,
			final List<Point> currentPath,
			final List<SolvedPath> allPaths) {
		if (x < 0 || y < 0 || x >= mazePanel.getCols() || y >= mazePanel.getRows()) {
			return;
		}
		if (mazePanel.isBlocked(x, y) || visited[y][x]) {
			return;
		}

		visited[y][x] = true;
		System.err.println("Visited: " + x + " " + y + " :: " + targetX + " " + targetY);
		currentPath.add(new Point(x, y));

		if (x == targetX && y == targetY) {
			allPaths.add(new SolvedPath(new ArrayList<>(currentPath)));
		} else {
			searchAllPaths(x + 1, y, targetX, targetY, visited, currentPath, allPaths); // right
			searchAllPaths(x, y + 1, targetX, targetY, visited, currentPath, allPaths); // down
			searchAllPaths(x - 1, y, targetX, targetY, visited, currentPath, allPaths);// left
			searchAllPaths(x, y - 1, targetX, targetY, visited, currentPath, allPaths); // up
		}

		currentPath.remove(currentPath.size() - 1);
		visited[y][x] = false;
	}

	private void clearSolutions() {
		solvedPaths.clear();
		pathList.clearSelection();
		mazePanel.setOrangePath(null);
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(MainFrame::new);
	}

}
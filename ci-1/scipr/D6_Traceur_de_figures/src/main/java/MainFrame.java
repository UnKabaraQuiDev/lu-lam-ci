import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.fasterxml.jackson.core.JsonProcessingException;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private DrawPanel drawPanel;

	private JList list;

	private JToolBar toolbar;

	private ButtonGroup toolGroup;

	public MainFrame() throws JsonProcessingException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		super.setTitle("Title hahaha 💅💅💅💅");

		getContentPane().setLayout(new BorderLayout());

		final JMenuBar menu = new JMenuBar();
		final JMenu fileMenu = new JMenu("File");

		final JMenuItem saveAsFileMenu = new JMenuItem("Save As...");
		saveAsFileMenu.addActionListener((a) -> drawPanel.triggerSaveAs());
		saveAsFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK));

		final JMenuItem importFileMenu = new JMenuItem("Import...");
		importFileMenu.addActionListener((a) -> drawPanel.triggerImport());
		importFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK));

		final JMenuItem openFileMenu = new JMenuItem("Open...");
		openFileMenu.addActionListener((a) -> drawPanel.triggerOpen());
		openFileMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));

		fileMenu.add(saveAsFileMenu);
		fileMenu.add(importFileMenu);
		fileMenu.add(openFileMenu);

		menu.add(fileMenu);
		super.setJMenuBar(menu);

		JPanel sidePanel = new JPanel();
		sidePanel.setLayout(new BorderLayout());

		list = new JList();
		sidePanel.add(new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		JButton colorBtn = new JButton("Change color");

		colorBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				colorDialog();
			}
		});

		sidePanel.add(colorBtn, BorderLayout.SOUTH);

		drawPanel = new DrawPanel(this);

		final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, drawPanel, sidePanel);
		splitPane.setDividerLocation(getWidth() - 100);
		getContentPane().add(splitPane, BorderLayout.CENTER);

		toolbar = new JToolBar(JToolBar.VERTICAL);
		JToggleButton select = new JToggleButton("Select & move & resize");
		JToggleButton rectangle = new JToggleButton("Rect");
		JToggleButton line = new JToggleButton("Line");

		toolGroup = new ButtonGroup();
		toolGroup.add(select);
		toolGroup.add(rectangle);
		toolGroup.add(line);
		
		select.addActionListener(a -> drawPanel.setUsingTool(new SelectTool()));
		rectangle.addActionListener(a -> drawPanel.setUsingTool(new RectangleTool()));
		line.addActionListener(a -> drawPanel.setUsingTool(new LineTool()));
		
		toolbar.add(select);
		toolbar.add(rectangle);
		toolbar.add(line);

		getContentPane().add(toolbar, BorderLayout.WEST);

		setVisible(true);
	}

	protected void colorDialog() {
		drawPanel.setColor(JColorChooser.showDialog(this, "Choix d'une couleur", drawPanel.getColor()));
		drawPanel.requestFocusInWindow();
	}

	public JList getList() {
		return list;
	}

	public JToolBar getToolbar() {
		return toolbar;
	}
	
	public ButtonGroup getToolGroup() {
		return toolGroup;
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

}

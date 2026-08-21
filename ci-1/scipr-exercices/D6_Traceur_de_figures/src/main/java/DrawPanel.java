import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {

	private MainFrame mainFrame;

	private Shapes shapes;

	private CustomShape currentShape;
	private Color color = Color.BLACK;

	private Tool usingTool;

	public DrawPanel(MainFrame mainFrame) {
		this(new Shapes(), mainFrame);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}

	public DrawPanel(Shapes lines, MainFrame mainFrame) {
		this.shapes = lines;
		this.mainFrame = mainFrame;

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());

		shapes.draw((Graphics2D) g);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		drawPanelMouseDragged(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		drawPanelMousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		drawPanelMouseReleased(e);
	}

	private void drawPanelMousePressed(MouseEvent e) {
		if (usingTool != null) {
			usingTool.onClick(e, this);
		}

		mainFrame.getList().setListData(shapes.getShapes().toArray());
	}

	private void drawPanelMouseReleased(MouseEvent e) {
		if (usingTool != null) {
			usingTool.onReleased(e, this);
		}

		this.repaint();
		mainFrame.getList().setListData(shapes.getShapes().toArray());
	}

	private void drawPanelMouseDragged(MouseEvent e) {
		if (usingTool != null) {
			usingTool.onDragged(e, this);
		}

		this.repaint();
		mainFrame.getList().setListData(shapes.getShapes().toArray());
	}

	public Shapes getShapes() {
		return shapes;
	}

	public void setShapes(Shapes lines) {
		this.shapes = lines;
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Tool getUsingTool() {
		return usingTool;
	}

	public void setUsingTool(Tool usingTool) {
		if(this.usingTool != null) {
			this.usingTool.onDeinit(this);
		}
		this.usingTool = usingTool;
		this.usingTool.onInit(this);
	}

	public CustomShape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(CustomShape currentShape) {
		this.currentShape = currentShape;
	}

	public void triggerSaveAs() {
		final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
		fileChooser.setName("Save As...");
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		final File file = fileChooser.getSelectedFile();
		try {
			if (file.exists()) {
				file.delete();
			}
			file.getParentFile().mkdirs();
			file.createNewFile();
			Files.write(Paths.get(file.getPath()), shapes.asJSON().getBytes(), StandardOpenOption.CREATE);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void triggerOpen() {
		final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
		fileChooser.setName("Open");
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		final File file = fileChooser.getSelectedFile();
		try {
			final String str = new String(Files.readAllBytes(Paths.get(file.getPath())));
			shapes.fromJSON(str);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void triggerImport() {
		final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
		fileChooser.setName("Import");
		if (fileChooser.showOpenDialog(this) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		final File file = fileChooser.getSelectedFile();
		try {
			final String str = new String(Files.readAllBytes(Paths.get(file.getPath())));
			shapes.appendJSON(str);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}

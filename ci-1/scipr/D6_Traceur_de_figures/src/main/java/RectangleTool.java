import java.awt.event.MouseEvent;

public class RectangleTool implements Tool {

	@Override
	public void onClick(MouseEvent e, DrawPanel panel) {
		panel.setCurrentShape(new Rectangle(panel.getColor(), e.getPoint(), e.getPoint()));
		panel.getShapes().add(panel.getCurrentShape());
	}

	@Override
	public void onReleased(MouseEvent e, DrawPanel panel) {
		panel.getCurrentShape().setEndpoint(e.getPoint());
		panel.setCurrentShape(null);
	}

	@Override
	public void onDragged(MouseEvent e, DrawPanel panel) {
		panel.getCurrentShape().setEndpoint(e.getPoint());
	}

}

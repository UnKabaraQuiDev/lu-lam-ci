import java.awt.event.MouseEvent;

public interface Tool {

	default void onInit(DrawPanel panel) {

	}

	void onDragged(MouseEvent e, DrawPanel panel);

	void onReleased(MouseEvent e, DrawPanel panel);

	void onClick(MouseEvent e, DrawPanel panel);

	default void onDeinit(DrawPanel panel) {

	}

}

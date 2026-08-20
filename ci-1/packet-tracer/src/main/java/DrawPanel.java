import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = -7770503646618737600L;

	private final List<Device> devices;
	private Consumer<Optional<Device>> selectionListener;
	private Device selectedDevice;
	private Device draggedDevice;
	private int dragOffsetX;
	private int dragOffsetY;

	private static final int DEVICE_WIDTH = 100;
	private static final int DEVICE_HEIGHT = 50;

	public DrawPanel(final List<Device> devices, Consumer<Optional<Device>> d) {
		this.devices = devices;
		this.setBackground(Color.WHITE);
		this.selectionListener = d;

		MouseAdapter mouseAdapter = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Device clicked = findDeviceAt(e.getX(), e.getY());
				selectedDevice = clicked;
				draggedDevice = clicked;

				if (clicked != null) {
					dragOffsetX = e.getX() - clicked.getX();
					dragOffsetY = e.getY() - clicked.getY();

					if (selectionListener != null) {
						selectionListener.accept(Optional.of(clicked));
					}
				}else {
					if (selectionListener != null) {
						selectionListener.accept(Optional.empty());
					}
				}

				repaint();
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (draggedDevice != null) {
					draggedDevice.setX(e.getX() - dragOffsetX);
					draggedDevice.setY(e.getY() - dragOffsetY);
					repaint();
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				draggedDevice = null;
			}
		};

		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
	}

	private Device findDeviceAt(int x, int y) {
		for (Device device : devices) {
			Device found = findDeviceAtRecursive(device, x, y);
			if (found != null) {
				return found;
			}
		}
		return null;
	}

	private Device findDeviceAtRecursive(Device device, int x, int y) {
		if (x >= device.getX() && x <= device.getX() + DEVICE_WIDTH && y >= device.getY() && y <= device.getY() + DEVICE_HEIGHT) {
			return device;
		}

		for (Device child : device.getSubDevices()) {
			Device found = findDeviceAtRecursive(child, x, y);
			if (found != null) {
				return found;
			}
		}

		return null;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		for (Device device : devices) {
			drawDeviceTree(g, device);
		}
	}

	private void drawDeviceTree(Graphics g, Device device) {
		for (Device child : device.getSubDevices()) {
			g.setColor(Color.BLACK);
			g
					.drawLine(device.getX() + DEVICE_WIDTH / 2,
							device.getY() + DEVICE_HEIGHT / 2,
							child.getX() + DEVICE_WIDTH / 2,
							child.getY() + DEVICE_HEIGHT / 2);
			drawDeviceTree(g, child);
		}

		switch (device.getPingStatus()) {
		case REACHABLE -> g.setColor(Color.GREEN);
		case FAILED -> g.setColor(Color.RED);
		case UNKNOWN -> g.setColor(Color.YELLOW);
		}

		g.fillRect(device.getX(), device.getY(), DEVICE_WIDTH, DEVICE_HEIGHT);

		if (device == selectedDevice) {
			g.setColor(Color.BLUE);
			g.drawRect(device.getX() - 2, device.getY() - 2, DEVICE_WIDTH + 4, DEVICE_HEIGHT + 4);
			g.drawRect(device.getX() - 3, device.getY() - 3, DEVICE_WIDTH + 6, DEVICE_HEIGHT + 6);
		}

		g.setColor(Color.BLACK);
		g.drawRect(device.getX(), device.getY(), DEVICE_WIDTH, DEVICE_HEIGHT);
		g.drawString(device.getName(), device.getX() + 10, device.getY() + 20);
		g.drawString(device.getType(), device.getX() + 10, device.getY() + 35);
		g.drawString(device.getIp(), device.getX() + 10, device.getY() + 50);
	}

}
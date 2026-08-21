import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = -4405285234843902636L;
	private final List<Device> devices;
	private final DrawPanel drawPanel;
	private final PingService pingService;

	private final JTextField nameField = new JTextField();
	private final JTextField typeField = new JTextField();
	private final JTextField ipField = new JTextField();
	private final JTextField parentField = new JTextField();

	public MainFrame(final List<Device> devices) {
		this.devices = devices != null ? devices : new ArrayList<>();
		this.pingService = new PingService();
		this.drawPanel = new DrawPanel(this.devices, this::onDeviceSelected);

		this.setTitle("Packet Tracer Lite");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setSize(1100, 750);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		this.add(this.createControlPanel(), BorderLayout.WEST);
		this.add(new JScrollPane(this.drawPanel), BorderLayout.CENTER);

		this.refreshPingStatuses();
	}

	private void onDeviceSelected(final Optional<Device> d) {
		d.ifPresentOrElse(f -> this.parentField.setText(f.getName()), () -> this.parentField.setText(""));
	}

	private JPanel createControlPanel() {
		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		panel.setPreferredSize(new Dimension(260, 700));

		panel.add(new JLabel("Name"));
		panel.add(this.nameField);

		panel.add(new JLabel("Type"));
		panel.add(this.typeField);

		panel.add(new JLabel("IP"));
		panel.add(this.ipField);

		panel.add(new JLabel("Parent name"));
		panel.add(this.parentField);

		final JButton addButton = new JButton("Add device");
		addButton.addActionListener(e -> this.addDevice());
		panel.add(addButton);

		final JButton pingButton = new JButton("Ping all");
		pingButton.addActionListener(e -> {
			this.refreshPingStatuses();
			this.drawPanel.repaint();
		});
		panel.add(pingButton);

		final JButton sampleButton = new JButton("Add sample PC");
		sampleButton.addActionListener(e -> {
			final Device sample = new Device(150, 150, "Router1", "PC", "PC-New", "8.8.8.8");
			this.addDeviceToStructure(sample);
			this.refreshPingStatuses();
			this.drawPanel.repaint();
		});
		panel.add(sampleButton);

		final JButton saveButton = new JButton("Save");
		saveButton.addActionListener(e -> saveDevices(this.devices));
		panel.add(saveButton);

		return panel;
	}

	private void addDevice() {
		final String name = this.nameField.getText().trim();
		final String type = this.typeField.getText().trim();
		final String ip = this.ipField.getText().trim();
		final String parent = this.parentField.getText().trim();

		if (name.isBlank()) {
			JOptionPane.showMessageDialog(this, "Name is required.");
			return;
		}

		final Device device = new Device(parent, type, name, ip);
		this.addDeviceToStructure(device);
		this.refreshPingStatuses();
		this.drawPanel.repaint();
		this.clearForm();
	}

	private void addDeviceToStructure(final Device device) {
		synchronized (this.devices) {
			if (device.getParent() == null || device.getParent().isBlank()) {
				this.devices.add(device);
				return;
			}

			final Device parentDevice = this.findDeviceByName(device.getParent(), this.devices);
			if (parentDevice != null) {
				parentDevice.getSubDevices().add(device);
			} else {
				this.devices.add(device);
			}
		}
	}

	private Device findDeviceByName(final String name, final List<Device> source) {
		for (final Device device : source) {
			if (name.equalsIgnoreCase(device.getName())) {
				return device;
			}
			final Device nested = this.findDeviceByName(name, device.getSubDevices());
			if (nested != null) {
				return nested;
			}
		}
		return null;
	}

	private void refreshPingStatuses() {
		new Thread(() -> {
			this.updateStatuses(this.devices);
			SwingUtilities.invokeLater(this.drawPanel::repaint);
		}).start();
	}

	private void updateStatuses(final List<Device> source) {
		synchronized (source) {
			for (final Device device : source) {
				device.setPingStatus(this.pingService.ping(device));
				this.updateStatuses(device.getSubDevices());
			}
		}
	}

	private void clearForm() {
		this.nameField.setText("");
		this.typeField.setText("");
		this.ipField.setText("");
		this.parentField.setText("");
	}

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(() -> {
			final List<Device> devices = loadDevices();
			final MainFrame frame = new MainFrame(devices);
			frame.setVisible(true);
		});
	}

	private static List<Device> loadDevices() {
		try {
			if (!Files.exists(Paths.get("./devices.json"))) {
				Files.writeString(Paths.get("./devices.json"), "[]");
			}

			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(new File("./devices.json"), new TypeReference<List<Device>>() {
			});
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static void saveDevices(final List<Device> devices) {
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
			objectMapper.writeValue(new File("./devices.json"), devices);
		} catch (final IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
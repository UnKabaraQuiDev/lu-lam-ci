package demographics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainFrame extends JFrame implements ChangeListener {

	private JSlider alphaSlider;
	private JSlider blueSlider;
	private DrawPanel drawPanel;
	private JSlider greenSlider;
	private JLabel redLabel;
	private JLabel greenLabel;
	private JLabel blueLabel;
	private JLabel alphaLabel;
	private JPanel sliderPanel;
	private JSlider redSlider;

	public MainFrame() {
		initComponents();

		drawPanel.setRed(redSlider);
		drawPanel.setGreen(greenSlider);
		drawPanel.setBlue(blueSlider);
		drawPanel.setAlpha(alphaSlider);
	}

	private void initComponents() {
		sliderPanel = new JPanel();
		
		redLabel = new JLabel();
		greenLabel = new JLabel();
		blueLabel = new JLabel();
		alphaLabel = new JLabel();
		
		redSlider = newSlider();
		greenSlider = newSlider();
		blueSlider = newSlider();
		alphaSlider = newSlider();
		
		drawPanel = new DrawPanel();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 600));

		sliderPanel.setLayout(new GridBagLayout());
		sliderPanel.setBackground(Color.decode("#ede8d0"));

		// labels
		redLabel.setText("Red");
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		sliderPanel.add(redLabel, gbc);

		greenLabel.setText("Green");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;;
		sliderPanel.add(greenLabel, gbc);

		blueLabel.setText("Blue");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		sliderPanel.add(blueLabel, gbc);

		alphaLabel.setText("Alpha");
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = GridBagConstraints.RELATIVE;
		sliderPanel.add(alphaLabel, gbc);

		// sliders
		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		sliderPanel.add(redSlider, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		sliderPanel.add(greenSlider, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		sliderPanel.add(blueSlider, gbc);

		gbc = new GridBagConstraints();
		gbc.gridx = 1;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.1;
		sliderPanel.add(alphaSlider, gbc);

		getContentPane().add(sliderPanel, BorderLayout.NORTH);

		/*GroupLayout drawPanel1Layout = new GroupLayout(drawPanel);
		drawPanel.setLayout(drawPanel1Layout);
		drawPanel1Layout.setHorizontalGroup(drawPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));
		drawPanel1Layout.setVerticalGroup(drawPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 367, Short.MAX_VALUE));*/

		getContentPane().add(drawPanel, BorderLayout.CENTER);

		pack();
	}

	private JSlider newSlider() {
		JSlider slider = new JSlider();
		slider.setMaximum(255);
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);
		slider.setToolTipText("");
		slider.addChangeListener(this);
		return slider;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		drawPanel.repaint();
	}

	public static void main(String args[]) {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

}

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

public class Printer extends JPanel {

	private JLabel name;
	private JLabel currentJobName;
	private JProgressBar currentJobProgress;

	private PrintJob currentJob;

	public Printer() {
		this.name = new JLabel("NAMEEEEE of le PrInTeUr");
		this.currentJobName = new JLabel();
		this.currentJobProgress = new JProgressBar();

		super.add(name);
		super.add(currentJobName);
		super.add(currentJobProgress);

		super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		super.setPreferredSize(new Dimension(200, 300));
		super.setAlignmentX(JPanel.LEFT_ALIGNMENT);

		update();
	}

	public Printer(String name) {
		this();
		this.name.setText(name);
		super.setBackground(Color.GRAY);
		update();
	}

	public void update() {
		if (currentJob != null && currentJob.isDone()) {
			currentJob = null;
		}
		if (currentJob == null) {
			this.currentJobName.setText("Unemployed 🥀🥀");
			this.currentJobProgress.setValue(0);
		} else {
			this.currentJobName.setText(currentJob.getTitle());
			this.currentJobProgress.setMaximum(currentJob.getDuration());
			this.currentJobProgress.setValue(currentJob.getElapsedTime());
		}
	}

	public boolean hasJob() {
		return this.currentJob == null || this.currentJob.isDone();
	}

	public boolean acceptJob(PrintJob pj) {
		if (hasJob()) {
			this.currentJob = pj;
			this.currentJob.setStartedTime(System.currentTimeMillis());
			return true;
		}
		return false;
	}

}

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PrinterManager extends JPanel {

	private List<Printer> printers = new ArrayList<>();
	private Queue<PrintJob> jobQueue = new ConcurrentLinkedQueue<>();
	private Timer unoptimizedSlop = new Timer(500, e -> updatePrinters());

	public PrinterManager(List<Printer> printers, Queue<PrintJob> jobQueue) {
		this.printers = printers;
		this.jobQueue = jobQueue;
		this.unoptimizedSlop.start();

		printers.forEach(super::add);
		start();
	}

	public PrinterManager(List<Printer> printers) {
		this.printers = printers;
		super.setLayout(new GridLayout(2, 2));
		printers.forEach(super::add);
		start();
	}

	public PrinterManager(int printerCount) {
		IntStream.range(0, printerCount).forEach(i -> printers.add(new Printer("Printeuuur#" + i)));
		printers.forEach(super::add);
		start();
	}

	private void start() {

		this.unoptimizedSlop.start();
		
		super.setLayout(new GridLayout(2, 2, 20, 20));
	}

	public void updatePrinters() {
		for (Printer p : printers) {
			if (jobQueue.peek() != null && p.acceptJob(jobQueue.peek())) {
				jobQueue.remove();
			}
			p.update();
		}
		repaint();
	}

	public void pushJob(PrintJob zob) {
		jobQueue.offer(zob);
	}

}

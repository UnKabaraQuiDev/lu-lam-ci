
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import org.joml.Vector2f;

import lu.pcy113.pclib.PCUtils;
import lu.pcy113.pclib.swing.JLineGraph;

public class MainFrame extends javax.swing.JFrame {

	private Balls balls;
	private JLineGraph graphPanel;

	public MainFrame() {
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		balls = new Balls(drawPanel1, graphPanel);
		drawPanel1.setBalls(balls);
		drawPanel1.setBackground(Color.WHITE);

		new Timer(1000 / 120, (e) -> updateView()).start();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {
		drawPanel1 = new DrawPanel();
		graphPanel = new JLineGraph();

		getContentPane().setLayout(new BorderLayout());

		final JSplitPane subPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, graphPanel.createLegend(true, true), graphPanel);
		subPanel.getLeftComponent().setMinimumSize(new Dimension(200, 0));
		subPanel.setPreferredSize(new Dimension(0, 200));
		subPanel.setDividerLocation(15);
		subPanel.validate();

		getContentPane().add(drawPanel1, java.awt.BorderLayout.CENTER);
		getContentPane().add(subPanel, java.awt.BorderLayout.NORTH);

		super.setMinimumSize(new Dimension(1200, 1000));

		super.setExtendedState(super.getExtendedState() | JFrame.MAXIMIZED_BOTH);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private long old = System.currentTimeMillis();

	public void updateView() {
		System.err.println("fps: " + ((double) 1000 / (System.currentTimeMillis() - old)));

		if (balls.size() < 500) {
			balls
					.add(new Ball(
							new Vector2f(PCUtils.randomIntRange(0, drawPanel1.getWidth()),
									PCUtils.randomIntRange(0, drawPanel1.getHeight())),
							new Vector2f((float) Math.random() * 2 - 1, (float) Math.random() * 2 - 1).normalize(),
							PCUtils.randomIntRange(5, 10)));
		}

		balls.doPhysics(1f / (System.currentTimeMillis() - old));
		drawPanel1.repaint();

		old = System.currentTimeMillis();
	}

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel. For
		 * details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private DrawPanel drawPanel1;
	// End of variables declaration//GEN-END:variables
}
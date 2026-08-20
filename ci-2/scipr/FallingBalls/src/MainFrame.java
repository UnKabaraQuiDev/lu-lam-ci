
import java.awt.Color;
import java.util.Random;

import lu.pcy113.pclib.PCUtils;

public class MainFrame extends javax.swing.JFrame {

	private Balls balls;

	public MainFrame() {
		initComponents();
		drawPanel1.setBalls(balls);
		drawPanel1.setBackground(Color.GRAY);

		final int[] samples = new int[] { 500, 1_000, 2_500, 5_000, 10_000, 25_000, 50_000, 100_000, 250_000, 500_000, 1_000_000 };
		for (int j = 0; j < samples.length; j++) {
			final int sample = samples[j];

			System.out.print("Sample count ---------------- " + sample + ": ");

			Random rand = new Random();
			for (int i = 0; i < sample; i++) {
				balls.add(new Ball(rand.nextInt(0, drawPanel1.getWidth()), rand.nextInt(0, drawPanel1.getHeight()), rand.nextInt(20, Balls.BALL_SIZE_MAX), Color.GREEN));
			}
			System.out.println("ok");

			balls.determineNumberOfNeighbors();

			balls = new Balls(drawPanel1);
			drawPanel1.setBalls(balls);
			System.gc();
			
			System.out.println(PCUtils.repeatString("\n", 3));
		}
		
		System.out.println("time,type,count");
		for(Balls.ScoreEntries se : Balls.reg) {
			System.out.println(se.time + "," + se.name + "," + se.count);
		}

		try {
			Thread.sleep(1_000); // buffer for timer
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.exit(12);
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		drawPanel1 = new DrawPanel();
		startStopBtn = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		startStopBtn.setText("Start Animation");

		javax.swing.GroupLayout drawPanel1Layout = new javax.swing.GroupLayout(drawPanel1);
		drawPanel1.setLayout(drawPanel1Layout);
		drawPanel1Layout.setHorizontalGroup(drawPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
				drawPanel1Layout.createSequentialGroup().addContainerGap(475, Short.MAX_VALUE).addComponent(startStopBtn).addGap(44, 44, 44)));
		drawPanel1Layout.setVerticalGroup(drawPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(drawPanel1Layout.createSequentialGroup().addGap(27, 27, 27).addComponent(startStopBtn).addContainerGap(366, Short.MAX_VALUE)));

		getContentPane().add(drawPanel1, java.awt.BorderLayout.CENTER);

		pack();

		balls = new Balls(drawPanel1);
	}// </editor-fold>//GEN-END:initComponents

	public void updateView() {
		balls.determineNumberOfNeighbors();
		drawPanel1.repaint();
	}

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
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
	private javax.swing.JButton startStopBtn;
	// End of variables declaration//GEN-END:variables
}

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import lu.pcy113.pclib.PCUtils;

public class MainFrame extends javax.swing.JFrame {

	RandomNumbers randomNumbers = new RandomNumbers();

	public MainFrame() {
		initComponents();
	}

	private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {
		randomNumbers.fill((int) nSpinner.getValue(), (int) minSpinner.getValue(), (int) maxSpinner.getValue());
		updateView();
	}

	private void linearButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		List<Long> testNumbers = new ArrayList<>();

		for (int i = 0; i < (int) searchCountSpinner.getValue(); i++) {
			testNumbers.add(PCUtils.randomLongRange((long) (int) minSpinner.getValue(), (long) (int) maxSpinner.getValue()));
		}
		
		long startTime = System.nanoTime();
		for (long num : testNumbers) {
			randomNumbers.linearSearch(num);
		}
		long stopTime = System.nanoTime();
		linearTime.setText("Time: " + String.valueOf((stopTime - startTime) / 1000000.0) + " ms");
	}

	private void binaryButtonActionPerformed(ActionEvent evt) {
		List<Long> testNumbers = new ArrayList<>();

		for (int i = 0; i < (int) searchCountSpinner.getValue(); i++) {
			testNumbers.add(PCUtils.randomLongRange((long) (int) minSpinner.getValue(), (long) (int) maxSpinner.getValue()));
		}
		
		long startTime = System.nanoTime();
		for (long num : testNumbers) {
			randomNumbers.binarySearch(num);
		}
		long stopTime = System.nanoTime();
		binaryTime.setText("Time: " + String.valueOf((stopTime - startTime) / 1000000.0) + " ms");
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
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	private void updateView() {
		jList1.setListData(randomNumbers.toArray());
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton binaryButton;
	private javax.swing.JLabel binaryTime;
	private javax.swing.JButton createButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JList jList1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JButton linearButton1;
	private javax.swing.JLabel linearTime;
	private javax.swing.JSpinner maxSpinner;
	private javax.swing.JSpinner minSpinner;
	private javax.swing.JSpinner nSpinner;
	private javax.swing.JSpinner searchCountSpinner;
	// End of variables declaration//GEN-END:variables

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		jList1 = new javax.swing.JList();
		jLabel1 = new javax.swing.JLabel();
		minSpinner = new javax.swing.JSpinner();
		maxSpinner = new javax.swing.JSpinner();
		jLabel2 = new javax.swing.JLabel();
		createButton = new javax.swing.JButton();
		jLabel3 = new javax.swing.JLabel();
		nSpinner = new javax.swing.JSpinner();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		binaryButton = new javax.swing.JButton();
		linearButton1 = new javax.swing.JButton();
		binaryTime = new javax.swing.JLabel();
		linearTime = new javax.swing.JLabel();
		searchCountSpinner = new javax.swing.JSpinner();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jScrollPane1.setViewportView(jList1);

		jLabel1.setText("Min");

		jLabel2.setText("Max");

		createButton.setText("Create");
		createButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createButtonActionPerformed(evt);
			}
		});

		jLabel3.setText("N");

		jLabel4.setText("Linear Search");

		jLabel5.setText("Binary Search");

		binaryButton.setText("start");
		binaryButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				binaryButtonActionPerformed(evt);
			}
		});

		linearButton1.setText("start");
		linearButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				linearButton1ActionPerformed(evt);
			}
		});

		binaryTime.setText("Time: ");

		linearTime.setText("Time: ");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap()
				.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(minSpinner)
						.addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(maxSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
						.addComponent(createButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(nSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
						.addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(binaryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(linearButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(binaryTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(linearTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(searchCountSpinner))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(jScrollPane1).addContainerGap())
				.addGroup(layout.createSequentialGroup().addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(minSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(maxSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(7, 7, 7)
						.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(nSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(createButton)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
						.addComponent(searchCountSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(linearButton1).addGap(3, 3, 3)
						.addComponent(linearTime, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(binaryButton).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(binaryTime, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(14, 14, 14)))));

		pack();
	}
}
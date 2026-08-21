package b15.catchthewolf;

public class CTWFrame extends javax.swing.JFrame {

	private RunningTrack runningTrack = new RunningTrack(25, 10);

	public CTWFrame() {
		initComponents();
		updateFields();
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jLabel1 = new javax.swing.JLabel();
        inputPanel = new javax.swing.JPanel();
        wolfInputLS = new javax.swing.JLabel();
        wolfInputSP = new javax.swing.JSpinner();
        wolfInputBtn = new javax.swing.JButton();
        playerInputLS = new javax.swing.JLabel();
        playerInputSP = new javax.swing.JSpinner();
        playerInputBtn = new javax.swing.JButton();
        progressPanel = new javax.swing.JPanel();
        playerProgressLS = new javax.swing.JLabel();
        playerPB = new javax.swing.JProgressBar();
        wolfProgressLS = new javax.swing.JLabel();
        wolfPB = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusCycleRoot(false);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Catch the Wolf !");

        inputPanel.setLayout(new java.awt.GridBagLayout());

        wolfInputLS.setText("Give the wolf  head start:");
        wolfInputLS.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        inputPanel.add(wolfInputLS, gridBagConstraints);

        wolfInputSP.setNextFocusableComponent(wolfInputSP);
        wolfInputSP.setPreferredSize(new java.awt.Dimension(80, 36));
        wolfInputSP.getModel().addChangeListener((ce) -> {
            try {
                int value = Integer.parseInt(wolfInputSP.getValue().toString());
                wolfInputSP.setValue(Math.max(1, value));
            }catch(NumberFormatException e) {
                wolfInputSP.setValue(0);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        inputPanel.add(wolfInputSP, gridBagConstraints);

        wolfInputBtn.setText("Start");
        wolfInputBtn.setNextFocusableComponent(playerInputSP);
        wolfInputBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wolfInputBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.ipadx = 28;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 25);
        inputPanel.add(wolfInputBtn, gridBagConstraints);

        playerInputLS.setText("Your step (1<=steps<=5):");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        inputPanel.add(playerInputLS, gridBagConstraints);

        playerInputSP.setNextFocusableComponent(playerInputBtn);
        playerInputSP.setPreferredSize(new java.awt.Dimension(80, 36));
        playerInputSP.getModel().addChangeListener((ce) -> {
            try {
                int value = Integer.parseInt(playerInputSP.getValue().toString());
                playerInputSP.setValue(Math.max(1, Math.min(5, value)));
            }catch(NumberFormatException e) {
                playerInputSP.setValue(0);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 30);
        inputPanel.add(playerInputSP, gridBagConstraints);

        playerInputBtn.setText("Run");
        playerInputBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playerInputBtnActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 36;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 25);
        inputPanel.add(playerInputBtn, gridBagConstraints);

        java.awt.GridBagLayout progressPanelLayout = new java.awt.GridBagLayout();
        progressPanelLayout.columnWidths = new int[] {0, 5, 0};
        progressPanelLayout.rowHeights = new int[] {0, 5, 0};
        progressPanel.setLayout(progressPanelLayout);

        playerProgressLS.setText("Player:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        progressPanel.add(playerProgressLS, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        progressPanel.add(playerPB, gridBagConstraints);

        wolfProgressLS.setText("Wolf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(11, 0, 11, 0);
        progressPanel.add(wolfProgressLS, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.2;
        progressPanel.add(wolfPB, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(progressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(inputPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(inputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(progressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playerInputBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playerInputBtnActionPerformed
		try {
			int steps = Integer.parseInt(playerInputSP.getValue().toString());
			steps = Math.max(1, Math.min(steps, 5));
			runningTrack.addPlayer(steps);
			runningTrack.addRandomWolf();

			updateFields();
		} catch (NumberFormatException e) {
			// to nothing 
		}
    }//GEN-LAST:event_playerInputBtnActionPerformed

    private void wolfInputBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wolfInputBtnActionPerformed
		try {
			runningTrack.start();
			runningTrack.addWolf(Integer.parseInt(wolfInputSP.getValue().toString()));

			updateFields();
		} catch (NumberFormatException e) {
			// to nothing 
		}
    }//GEN-LAST:event_wolfInputBtnActionPerformed

	private void updateFields() {
		playerPB.setValue((int) ((double) runningTrack.getPlayerPosition() / runningTrack.getMaxPosition() * 100));
		wolfPB.setValue((int) ((double) runningTrack.getWolfPosition() / runningTrack.getMaxPosition() * 100));

		if (runningTrack.isEndReached() || runningTrack.wolfCaught()) {
			runningTrack.stop();
		}

		if (runningTrack.isRunning()) {
			playerPB.setEnabled(true);
			wolfPB.setEnabled(true);

			wolfInputSP.setEnabled(false);
			wolfInputBtn.setEnabled(false);

			playerInputSP.setEnabled(true);
			playerInputBtn.setEnabled(true);
		} else {
			wolfInputSP.setEnabled(true);
			wolfInputBtn.setEnabled(true);

			playerInputSP.setEnabled(false);
			playerInputBtn.setEnabled(false);

			if (runningTrack.wolfCaught()) {
				playerPB.setEnabled(false);
				wolfPB.setEnabled(false);
			} else if (runningTrack.wolfEnded()) {
				playerPB.setEnabled(false);
			} else if (runningTrack.playerEnded()) {
				wolfPB.setEnabled(false);
			}
		}
	}

	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(CTWFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(CTWFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(CTWFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(CTWFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new CTWFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel inputPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton playerInputBtn;
    private javax.swing.JLabel playerInputLS;
    private javax.swing.JSpinner playerInputSP;
    private javax.swing.JProgressBar playerPB;
    private javax.swing.JLabel playerProgressLS;
    private javax.swing.JPanel progressPanel;
    private javax.swing.JButton wolfInputBtn;
    private javax.swing.JLabel wolfInputLS;
    private javax.swing.JSpinner wolfInputSP;
    private javax.swing.JProgressBar wolfPB;
    private javax.swing.JLabel wolfProgressLS;
    // End of variables declaration//GEN-END:variables

}

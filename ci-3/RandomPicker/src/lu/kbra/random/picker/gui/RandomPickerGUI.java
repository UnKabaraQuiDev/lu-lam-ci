package lu.kbra.random.picker.gui;

import java.util.Arrays;
import java.util.stream.Collectors;

public class RandomPickerGUI extends javax.swing.JFrame {

	private Activities activities = new Activities();
	
    public RandomPickerGUI() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        addLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        addTextField = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        pickRandomActivityButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        activityList = new javax.swing.JList();
        clearActivityListButton = new javax.swing.JButton();
        deleteSelectedActivityButton = new javax.swing.JButton();
        shuffleActivityButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        addLabel.setText("Activity");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 15);
        jPanel1.add(addLabel, gridBagConstraints);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 6;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(0, 15, 0, 0);
        jPanel1.add(addButton, gridBagConstraints);

        addTextField.setToolTipText("");
        addTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(addTextField, gridBagConstraints);

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWidths = new int[] {0};
        jPanel2Layout.rowHeights = new int[] {0, 5, 0, 5, 0, 5, 0, 5, 0};
        jPanel2.setLayout(jPanel2Layout);

        pickRandomActivityButton.setText("Pick random activity");
        pickRandomActivityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pickRandomActivityButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(pickRandomActivityButton, gridBagConstraints);

        activityList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Learn", "Eat", "Sleep" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        activityList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(activityList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jScrollPane1, gridBagConstraints);

        clearActivityListButton.setText("Clear list");
        clearActivityListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActivityListButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(clearActivityListButton, gridBagConstraints);

        deleteSelectedActivityButton.setText("Delete selected activity");
        deleteSelectedActivityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectedActivityButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(deleteSelectedActivityButton, gridBagConstraints);

        shuffleActivityButton.setText("Shuffle");
        shuffleActivityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shuffleActivityButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel2.add(shuffleActivityButton, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
		activities.add(new Activity(addTextField.getText()));
		updateList();
    }//GEN-LAST:event_addButtonActionPerformed

    private void pickRandomActivityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pickRandomActivityButtonActionPerformed
		activityList.setSelectedIndex((int) (Math.random()*activities.size()));
    }//GEN-LAST:event_pickRandomActivityButtonActionPerformed

    private void clearActivityListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActivityListButtonActionPerformed
		activities.clear();
		updateList();
    }//GEN-LAST:event_clearActivityListButtonActionPerformed

    private void deleteSelectedActivityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSelectedActivityButtonActionPerformed
		activities.remove(activityList.getSelectedIndex());
		updateList();
    }//GEN-LAST:event_deleteSelectedActivityButtonActionPerformed

    private void shuffleActivityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shuffleActivityButtonActionPerformed
		activities.shuffle2();
		updateList();
    }//GEN-LAST:event_shuffleActivityButtonActionPerformed

    private void addTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTextFieldActionPerformed
        addButton.doClick();
    }//GEN-LAST:event_addTextFieldActionPerformed

	private void updateList() {
		activityList.setListData(activities.toArray());
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
            java.util.logging.Logger.getLogger(RandomPickerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RandomPickerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RandomPickerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RandomPickerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RandomPickerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList activityList;
    private javax.swing.JButton addButton;
    private javax.swing.JLabel addLabel;
    private javax.swing.JTextField addTextField;
    private javax.swing.JButton clearActivityListButton;
    private javax.swing.JButton deleteSelectedActivityButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pickRandomActivityButton;
    private javax.swing.JButton shuffleActivityButton;
    // End of variables declaration//GEN-END:variables

}

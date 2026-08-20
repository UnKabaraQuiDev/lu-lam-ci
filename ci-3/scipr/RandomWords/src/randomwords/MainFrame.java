package randomwords;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MainFrame extends javax.swing.JFrame {

	private List<Word> words = new ArrayList<Word>();

	public MainFrame() {
		initComponents();

		try {
			Scanner fr = new Scanner(new File("./words.txt"));
			String line = null;
			int i = 0, max = 20;
			while ((line = fr.nextLine()) != null && words.size() < max) {
				if (Math.random() < 0.5f) {
					Word w = new Word(line);
					if (!w.getRealWord().equals(w.getDisplayWord())) {
						words.add(new Word(line));
					}
				}

				if (!fr.hasNextLine()) {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		updateView();
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jSeparator1 = new javax.swing.JSeparator();
        inputField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setMinimumSize(new java.awt.Dimension(450, 300));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(jScrollPane1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        getContentPane().add(jSeparator1, gridBagConstraints);

        inputField.setToolTipText("Enter text...");
        inputField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 0.1;
        getContentPane().add(inputField, gridBagConstraints);

        jButton1.setText("Check >>");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jButton1, gridBagConstraints);

        jButton2.setText("Hint !");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2inputFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        getContentPane().add(jButton2, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFieldActionPerformed

		for (int index = 0; index < words.size(); index++) {
			Word w = words.get(index);
			if (w.getRealWord().equals(inputField.getText())) {
				w.setDisplayWord("[V] " + w.getRealWord());
				updateView();
				jList1.setSelectedIndex(index);
				inputField.setText("");
				break;
			}
		}

    }//GEN-LAST:event_inputFieldActionPerformed

    private void jButton2inputFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2inputFieldActionPerformed
		int index = 0;
		for (Word w : words) {
			if (!w.getRealWord().equals(w.getDisplayWord())) {
				w.unshuffle(1);
				updateView();
				jList1.setSelectedIndex(index);
				break;
			}

			index++;
		}
    }//GEN-LAST:event_jButton2inputFieldActionPerformed

	private void updateView() {
		jList1.setListData(words.toArray(new Object[words.size()]));
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
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		System.out.println(Arrays.toString("Starship".split("|")));

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField inputField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

}

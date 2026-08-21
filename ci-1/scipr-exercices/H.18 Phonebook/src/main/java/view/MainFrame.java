package view;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import model.DataSource;
import model.FileDataSource;
import model.Person;
import model.PersonList;
import model.SQLDataSource;

public class MainFrame extends JFrame {

	protected static final String DATA_SOURCE_FILE = "File";
	protected static final String DATA_SOURCE_SQL = "SQL";
	protected static final String DATA_SOURCE_SQL_PCLIB = "SQL PCLib";

	protected static final String[] DATA_SOURCE_OPTIONS = { DATA_SOURCE_FILE, DATA_SOURCE_SQL, DATA_SOURCE_SQL_PCLIB };

	protected PersonList pl = new PersonList();
	protected int index = 0;

	protected DataSource dataSource = new FileDataSource();

	public MainFrame() {
		initComponents();
		dataSource.load(pl);
		pl.sort();
		updateView();
	}

	public void updateView() {
		if (pl.size() == 0) {
			index = 0;
			tfName.setText("");
			tfName.setEnabled(false);
			tfEmail.setText("");
			tfEmail.setEnabled(false);
			tfPhone.setText("");
			tfPhone.setEnabled(false);
			lblOutput.setText("Record " + 0 + " of " + 0);
			btnNext.setEnabled(false);
			btnBack.setEnabled(false);
			btnRemove.setEnabled(false);
			return;
		}

		tfName.setText(pl.getPerson(index).getName());
		tfEmail.setText(pl.getPerson(index).getEmail());
		tfPhone.setText(pl.getPerson(index).getPhone());
		lblOutput.setText("Record " + String.valueOf(index + 1) + " of " + String.valueOf(pl.size()));

		tfName.setEnabled(true);
		tfEmail.setEnabled(true);
		tfPhone.setEnabled(true);

		if (index == pl.size() - 1) {
			btnNext.setEnabled(false);
		} else {
			btnBack.setEnabled(true);
			btnNext.setEnabled(true);
		}
		if (index == 0) {
//			btnRemove.setEnabled(false);
			btnBack.setEnabled(false);
		} else {
			btnRemove.setEnabled(true);
			btnBack.setEnabled(true);
		}
	}

	private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed
		index++;
		updateView();
	}// GEN-LAST:event_btnNextActionPerformed

	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddActionPerformed
		final String name = tfName.getText();
		final String email = tfEmail.getText();
		final String phone = tfPhone.getText();
		final Person p = dataSource.insert(pl);
		p.setEmail(email);
		p.setName(name);
		p.setPhone(phone);
//		pl.add(new Person(name, email, phone));
//		index = 0;
		index = pl.size() - 1;
//		pl.sort();
		updateView();
	}// GEN-LAST:event_btnAddActionPerformed

	private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRemoveActionPerformed
		pl.removeIndex(index);
		index = 0;
		updateView();
	}// GEN-LAST:event_btnRemoveActionPerformed

	private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnBackActionPerformed
		index--;
		updateView();
	}// GEN-LAST:event_btnBackActionPerformed

	private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSaveActionPerformed
		final String name = tfName.getText();
		final String email = tfEmail.getText();
		final String phone = tfPhone.getText();
		final Person p = pl.getPerson(index);
		p.setEmail(email);
		p.setName(name);
		p.setPhone(phone);
		dataSource.save(pl);
	}// GEN-LAST:event_btnSaveActionPerformed

	/**
	 * @param args the command line arguments
	 */
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

	private void initComponents() {

		btnBack = new javax.swing.JButton();
		lblOutput = new javax.swing.JLabel();
		btnNext = new javax.swing.JButton();
		btnAdd = new javax.swing.JButton();
		btnRemove = new javax.swing.JButton();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		tfName = new javax.swing.JTextField();
		tfEmail = new javax.swing.JTextField();
		tfPhone = new javax.swing.JTextField();
		btnSave = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		btnBack.setText("<");
		btnBack.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnBackActionPerformed(evt);
			}
		});

		lblOutput.setText("Record 0 of 0");

		btnNext.setText(">");
		btnNext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnNextActionPerformed(evt);
			}
		});

		btnAdd.setText("*");
		btnAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnAddActionPerformed(evt);
			}
		});

		btnRemove.setText("X");
		btnRemove.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRemoveActionPerformed(evt);
			}
		});

		jLabel2.setText("Name");

		jLabel3.setText("Phone");

		jLabel4.setText("Email");

		btnSave.setText("Save");
		btnSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSaveActionPerformed(evt);
			}
		});

		comboBox = new JComboBox<>(DATA_SOURCE_OPTIONS);
		comboBox.addActionListener(e -> {
			final String selected = (String) comboBox.getSelectedItem();
//			System.out.println("Selected: " + selected);
			dataSource = switch (selected) {
			case DATA_SOURCE_FILE -> new FileDataSource();
			case DATA_SOURCE_SQL -> new SQLDataSource();
			case DATA_SOURCE_SQL_PCLIB -> new SQLPCLibDataSource();
			default -> throw new IllegalArgumentException(selected);
			};
			pl = new PersonList();
			dataSource.load(pl);
			index = 0;
			updateView();
		});

//		getContentPane().add(comboBox);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(tfPhone))
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 54,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addComponent(lblOutput, javax.swing.GroupLayout.PREFERRED_SIZE,
																197, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
														.addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE,
																54, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE,
																54, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE,
																54, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(tfName)))
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(tfEmail))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE).addComponent(btnSave).addComponent(comboBox)))
						.addContainerGap()));
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel2).addComponent(tfName,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(22, 22, 22)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel4)
										.addComponent(tfEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(21, 21, 21)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel3).addComponent(tfPhone,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18).addComponent(btnSave).addComponent(comboBox)
								.addContainerGap(17, Short.MAX_VALUE)));

		pack();
	}

	private javax.swing.JButton btnAdd;
	private javax.swing.JButton btnBack;
	private javax.swing.JButton btnNext;
	private javax.swing.JButton btnRemove;
	private javax.swing.JButton btnSave;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel lblOutput;
	private javax.swing.JTextField tfEmail;
	private javax.swing.JTextField tfName;
	private javax.swing.JTextField tfPhone;
	private JComboBox<String> comboBox;

}

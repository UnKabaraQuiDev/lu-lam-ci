package lu.kbra.customarraylist;

import java.util.Arrays;

public class MainFrame extends javax.swing.JFrame {

	public MainFrame() {
		MyArrayList<String> arr = new MyArrayList<>();

		System.out.println(arr);

		arr.add("0a");
		arr.add("1a");
		arr.add("2a");
		arr.add("3a");
		arr.add("4a");
		arr.add("5a");
		arr.add("6a");
		arr.add("7a");
		arr.add("8a");
		arr.add("9");

		System.out.println(arr);

		for (int i = 0; i < 10; i++) {
			System.out.println(arr.get(i));
		}

		arr.add("9");
		arr.add("9");
		arr.add("9");
		arr.add("9");
		System.out.println(arr);

		bubbleSort();

		initComponents();
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MainFrame().setVisible(true);
			}
		});
	}

	private void bubbleSort() {

		int[] arr = new int[100];
		randomFill(arr);

		System.out.println(Arrays.toString(arr));

		boolean changed = false;
		do {
			changed = false;

			for (int i = 0; i < arr.length - 1; i++) {

				if (arr[i] > arr[i + 1]) {
					int swap = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = swap;

					changed = true;
				}

			}
		} while (changed);

		System.out.println(Arrays.toString(arr));
	}

	private void randomFill(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 100);
		}

	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

package qsd;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MainFrame extends JFrame {

	private SpellChecker checker;

	private final JTextArea inputArea;
	private final JTextArea outputArea;
	private final JComboBox<String> comboBox;

	public MainFrame(SpellChecker checker) {
		this.checker = checker;

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 500);

		inputArea = new JTextArea();
		outputArea = new JTextArea();
		outputArea.setEditable(false);

		String[] options = { "ArrayList", "TreeSet" };
		this.comboBox = new JComboBox<>(options);

		comboBox.addActionListener(this::comboBoxAction);

		final JButton checkButton = new JButton("Check");
		checkButton.addActionListener(e -> checkText());

		setLayout(new BorderLayout());
		add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(inputArea), new JScrollPane(outputArea)), BorderLayout.CENTER);
		add(checkButton, BorderLayout.NORTH);
		add(comboBox, BorderLayout.SOUTH);
	}

	private void checkText() {
		long start = System.nanoTime();
		List<Misspelling> errors = checker.analyseText(inputArea.getText());
		long end = System.nanoTime();

		StringBuilder sb = new StringBuilder();
		sb.append("Time: ").append((end - start) / 1_000_000.0).append(" ms\n\n");
		for (Misspelling m : errors) {
			sb.append(m).append("\n");
		}
		outputArea.setText(sb.toString());
		((JScrollPane) SwingUtilities.getAncestorOfClass(JScrollPane.class, outputArea)).getVerticalScrollBar().setValue(0);
	}

	public static void main(String[] args) throws Exception {
		final Dictionnary dict = new TreeSetDict();
		final SpellChecker checker = new SpellChecker(dict);

		SwingUtilities.invokeLater(() -> new MainFrame(checker).setVisible(true));
	}

	private void comboBoxAction(ActionEvent actionevent1) {
		String selected = (String) comboBox.getSelectedItem();
		System.out.println("Selected: " + selected);
		try {
			checker.setDictionary(switch (selected) {
			case "TreeSet" -> new TreeSetDict();
			case "ArrayList" -> new ArrayListDict();
			default -> throw new IllegalArgumentException(selected);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
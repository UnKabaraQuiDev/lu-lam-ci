import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainFrame extends JFrame {

	public class StatsEntry {
		public String word;
		public int count;

		public StatsEntry(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public String toString() {
			return word + " --> " + count;
		}

	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private HashMap<String, Integer> wordMap = new HashMap<>();

	private List<StatsEntry> entries = new ArrayList<>();

	private JTextArea textArea;
	private JList list;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		setLayout(new BorderLayout());

		textArea = new JTextArea("text");
		list = new JList();

		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateWordMap();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateWordMap();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateWordMap();
			}
		});

		final JScrollPane textAreaScrolPane = new JScrollPane(textArea);
		final JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, textAreaScrolPane, new JScrollPane(list));
		textAreaScrolPane.setMinimumSize(new Dimension(250, 100));
		add(splitPane, BorderLayout.CENTER);
	}

	private void updateWordMap() {
		wordMap.clear();
		entries.clear();

		String text = textArea.getText().toLowerCase();
		String[] words = text.split("\\W+");

		for (String word : words) {
			if (!word.isEmpty()) {
				wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);

				entries
						.stream()
						.filter(e -> e.word.equals(word))
						.findFirst()
						.ifPresentOrElse((e) -> e.count++, () -> entries.add(new StatsEntry(word, 1)));
			}
		}

		/*
		 * list .setListData(wordMap .entrySet() .stream() .sorted((e1, e2) ->
		 * -Integer.compare(e1.getValue(), e2.getValue())) .map(k -> k.getKey() + " --> " + k.getValue())
		 * .toArray());
		 */

		entries.sort((e1, e2) -> -Integer.compare(e1.count, e2.count));
		list.setListData(entries.toArray());
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

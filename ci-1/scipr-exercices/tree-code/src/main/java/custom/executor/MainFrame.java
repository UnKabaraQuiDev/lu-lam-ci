package custom.executor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.StringReader;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import lu.pcy113.pclib.PCUtils;

import custom.parser.CustomParser;

public class MainFrame extends JFrame {

	private final DrawPanel drawPanel;
	private final JTextPane codeEditor;
	private final CodeDiagramPanel diagramPanel;

	public MainFrame(Consumer<Pen> executor) {
		super("MainFrame");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		drawPanel = new DrawPanel();
		drawPanel.setExecutor(executor);
		add(drawPanel, BorderLayout.CENTER);

		codeEditor = new JTextPane();
		codeEditor.setText(PCUtils.readPackagedStringFile("/test.tc"));
		codeEditor.setFont(new Font("Monospaced", Font.PLAIN, 12));
		final JScrollPane codeScroll = new JScrollPane(codeEditor);
		codeScroll.setPreferredSize(new Dimension(250, 400));
		add(codeScroll, BorderLayout.WEST);

		final StyledDocument doc = codeEditor.getStyledDocument();
		final Style defaultStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setForeground(defaultStyle, Color.BLACK);

		final Style commandStyle = doc.addStyle("command", null);
		StyleConstants.setForeground(commandStyle, Color.BLUE);
		StyleConstants.setBold(commandStyle, true);

		final Style numberStyle = doc.addStyle("number", null);
		StyleConstants.setForeground(numberStyle, Color.MAGENTA);

		final Style colorStyle = doc.addStyle("color", null);
		StyleConstants.setForeground(colorStyle, Color.ORANGE);
		codeEditor.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateStyles();
				recompile();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateStyles();
				recompile();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateStyles();
				recompile();
			}
		});

		diagramPanel = new CodeDiagramPanel();
		final JScrollPane diagramScroll = new JScrollPane(diagramPanel);
		diagramScroll.setPreferredSize(new Dimension(250, 400));
		add(diagramScroll, BorderLayout.EAST);

		setSize(1000, 600);
		setVisible(true);
	}

	private void updateStyles() {
		SwingUtilities.invokeLater(() -> highlight(codeEditor, codeEditor.getStyledDocument()));
	}

	private void recompile() {
		SwingUtilities.invokeLater(() -> {
			try {
				final String code = codeEditor.getText();
				drawPanel.setExecutor(new BasicExecutor(new CustomParser(new StringReader(code)).getRoot()));
				drawPanel.repaint();
			} catch (Exception e) {
				System.err.println(e);
			}
		});
	}

	private static void highlight(JTextPane editor, StyledDocument doc) {
		if (true)
			return;

		String text = editor.getText();
		// Reset to default
		doc.setCharacterAttributes(0, text.length(), doc.getStyle(StyleContext.DEFAULT_STYLE), true);

		// Highlight commands
		Pattern commandPattern = Pattern.compile("\\b(move|rotate|up|down|thickness|color|goto|loop)\\b");
		Matcher m = commandPattern.matcher(text);
		while (m.find()) {
			doc.setCharacterAttributes(m.start(), m.end() - m.start(), doc.getStyle("command"), true);
		}

		// Highlight numbers (integers or floats)
		Pattern numberPattern = Pattern.compile("\\b\\d+(\\.\\d+)?\\b");
		m = numberPattern.matcher(text);
		while (m.find()) {
			doc.setCharacterAttributes(m.start(), m.end() - m.start(), doc.getStyle("number"), true);
		}

		// Highlight color literals "#xxxxxx"
		Pattern colorPattern = Pattern.compile("\"#[0-9a-fA-F]{6}\"");
		m = colorPattern.matcher(text);
		while (m.find()) {
			doc.setCharacterAttributes(m.start(), m.end() - m.start(), doc.getStyle("color"), true);
		}
	}

	public static class CodeDiagramPanel extends JPanel {
		public CodeDiagramPanel() {
			setBackground(Color.WHITE);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.BLUE);
			g.drawString("Digagram", 20, 20);
		}
	}

}

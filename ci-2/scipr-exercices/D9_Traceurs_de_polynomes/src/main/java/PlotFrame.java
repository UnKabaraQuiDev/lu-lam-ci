import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class PlotFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private MainFrame mainFrame;
	private DrawPanel drawPanel;

	/**
	 * Create the frame.
	 */
	public PlotFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		drawPanel = new DrawPanel(this);
		contentPane.add(drawPanel);
	}

	public DrawPanel getDrawPanel() {
		return drawPanel;
	}

}

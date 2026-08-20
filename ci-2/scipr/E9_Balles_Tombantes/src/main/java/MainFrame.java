import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private DrawPanel drawPanel;
	
	private JPanel footer = new JPanel();
	private JSlider nbrOfBallsSlider;
	private JButton startButton;
	
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());

		nbrOfBallsSlider = new JSlider();
		nbrOfBallsSlider.setMaximum(100);
		
		startButton = new JButton("Start Button");
		
		startButton.addActionListener(a -> {
			drawPanel.getBalls().clear();
			for(int i = 0; i < nbrOfBallsSlider.getValue(); i++) {
				drawPanel.getBalls().addBall();
			}
			
			drawPanel.timerMove.start();
			drawPanel.timerDrop.start();
		});
		
		footer.setLayout(new BorderLayout());
		footer.add(nbrOfBallsSlider);
		footer.add(startButton, BorderLayout.WEST);
		
		contentPane.add(footer, BorderLayout.SOUTH);
		
		super.setExtendedState(JFrame.MAXIMIZED_BOTH);
		super.setVisible(true);

		drawPanel = new DrawPanel(this, new Balls());

		drawPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON2) {
				drawPanel.getBalls().addBall();
					return;
				}
				
				drawPanel.getBalls().setSpeed(Balls.DEFAULT_SPEED * 0.3);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				drawPanel.getBalls().setSpeed(Balls.DEFAULT_SPEED);
			}
		});

		contentPane.add(drawPanel);

		setContentPane(contentPane);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

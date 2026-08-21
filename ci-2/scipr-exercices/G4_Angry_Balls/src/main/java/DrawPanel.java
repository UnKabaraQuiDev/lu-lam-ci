
import java.awt.Color;
import java.awt.Graphics;

public class DrawPanel extends javax.swing.JPanel {

	private Game game = null;

	public DrawPanel() {
		initComponents();
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (game != null) {
			game.draw(g);
		}
	}

	private void initComponents() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
	}

}

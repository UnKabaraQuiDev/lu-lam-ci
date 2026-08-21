


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {
	private Game game;

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.BLACK);
		if (game != null)
			game.draw(g);
	}
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package demographics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;

import javax.swing.JSlider;

public class DrawPanel extends javax.swing.JPanel {

	public DrawPanel() {
		initComponents();
	}

	private JSlider red, green, blue, alpha;

	private int count = 20;

	@Override
	public void paint(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		double colWidth = (double) getWidth() / count;
		double rowHeight = (double) getHeight() / count;

		g.setColor(Color.BLACK);
		for (int x = 0; x <= count; x++) {
			g.drawLine((int) (x * colWidth), 0, getWidth(), (int) ((count - x) * rowHeight));
			g.drawLine(0, (int) (x * rowHeight), (int) ((count - x) * colWidth), getHeight());

			g.drawLine(0, (int) (x * rowHeight), (int) (x * colWidth), 0);
			g.drawLine((int) (x * colWidth), getHeight(), getWidth(), (int) (x * rowHeight));
		}

		if(red == null) {
			return;
		}
		
		rowHeight = (double) getHeight() / 3;
		colWidth = (double) getWidth() / 6;

		fillRect(g, 0, 0, 2, 1, colWidth, rowHeight, new Color(red.getValue(), 0, 0));
		fillRect(g, 2, 0, 2, 1, colWidth, rowHeight, new Color(red.getValue(), green.getValue(), 0));
		fillRect(g, 4, 0, 2, 1, colWidth, rowHeight, new Color(0, green.getValue(), 0));
		
		fillRect(g, 1, 1, 1, 1, colWidth, rowHeight, new Color(red.getValue(), 0, blue.getValue()));
		fillRect(g, 2, 1, 2, 1, colWidth, rowHeight, new Color(red.getValue(), green.getValue(), blue.getValue()));
		fillRect(g, 4, 1, 1, 1, colWidth, rowHeight, new Color(0, green.getValue(), blue.getValue()));
		
		fillRect(g, 2, 2, 2, 1, colWidth, rowHeight, new Color(0, 0, blue.getValue()));
		
		g.dispose();
	}
	
	private void fillRect(Graphics g, int x0, int y0, int w, int h, double width, double height, Color color) {
		if(alpha != null) {
			g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha.getValue()));
		}
		g.fillRect((int) (x0 * width), (int) (y0 * height), (int) (w * width), (int) (h * height));
	}
	
	public void setRed(JSlider red) {
		this.red = red;
	}

	public void setGreen(JSlider green) {
		this.green = green;
	}

	public void setBlue(JSlider blue) {
		this.blue = blue;
	}

	public void setAlpha(JSlider alpha) {
		this.alpha = alpha;
	}

    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }

}

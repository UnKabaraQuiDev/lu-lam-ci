package ponggame;

import java.awt.Color;
import java.awt.Graphics2D;
import lu.pcy113.pclib.PCUtils;

public class Platform {

	private int width, height;
	private double x, y;
	private double velY;

	public Platform(int width, int height, double x, double y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
	}

	public void update() {
		y = PCUtils.clamp(0, DrawPanel.HEIGHT - height, y + velY);
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillRect((int) x, (int) y, width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

}

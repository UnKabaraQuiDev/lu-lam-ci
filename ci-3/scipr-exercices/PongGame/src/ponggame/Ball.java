package ponggame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Ball {

	private int radius;
	private double x, y;
	private double velX, velY;

	public Ball(int radius, double x, double y, double velX, double velY) {
		this.radius = radius;
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
	}

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.WHITE);
		g2d.fillOval((int) x - radius, (int) y - radius, 2 * radius, 2 * radius);
	}

	public void update() {
		x += velX;
		if (x - radius < 0 || x + radius > DrawPanel.WIDTH) {
			System.out.println("X overload: " + x);
			x -= velX;
			velX = -velX;
			DrawPanel.endGame();
			playSound("buzz.wav");
		}
		y += velY;
		if (y - radius < 0 || y + radius > DrawPanel.HEIGHT) {
			System.out.println("Y overload: " + y);
			y -= velY;
			velY = -velY;
		}

		if (rightSideHit() || leftSideHit()) {
			velX = -velX;
			playSound("boing.wav");
		}
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
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

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	private boolean rightSideHit() {
		return x + radius > DrawPanel.platformRight.getX() && y > DrawPanel.platformRight.getY() && y < DrawPanel.platformRight.getY() + DrawPanel.platformRight.getHeight();
	}

	private boolean leftSideHit() {
		return x - radius < DrawPanel.platformLeft.getX() + DrawPanel.platformLeft.getWidth() && y > DrawPanel.platformLeft.getY() && y < DrawPanel.platformLeft.getY() + DrawPanel.platformLeft.getHeight();
	}

	public static void playSound(String soundFilePath) {
        try {
            // Get the audio input stream from the sound file
            File soundFile = new File(soundFilePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            // Get a sound clip resource
            Clip clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream
            clip.open(audioInputStream);

            // Play the audio clip
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            ex.printStackTrace();
        }
    }

}

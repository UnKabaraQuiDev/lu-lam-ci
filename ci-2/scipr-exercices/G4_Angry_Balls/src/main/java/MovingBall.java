
import java.awt.Color;

public class MovingBall extends Ball {

	private double dX = 0.0, dY = 0.0;

	public MovingBall(double x, double y, int radius, Color color) {
		super(x, y, radius, color);
	}

	public void move() {
		x += dX;
		y += dY;
		dY += 0.15;
	}

	public double getdX() {
		return dX;
	}

	public double getdY() {
		return dY;
	}

	public void setdX(double dX) {
		this.dX = dX;
	}

	public void setdY(double dY) {
		this.dY = dY;
	}

}

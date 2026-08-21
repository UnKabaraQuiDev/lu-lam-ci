
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import lu.pcy113.pclib.PCUtils;

public class Polynomial {

	private ArrayList<Double> alCoefficients = new ArrayList<>();

	private double xMin, xMax, yMin, yMax;

	public Polynomial() {
		setLimits(-2, 2, -2, 2);
	}

	public void draw(DrawPanel panel, Graphics2D g2d) {
		g2d.setColor(Color.BLUE);

		for (int px = 0; px < panel.getWidth(); px++) {

			double xFac = (xMax - xMin) / panel.getWidth(), yFac = (yMax - yMin) / panel.getHeight();

			double xGraph = xFac * px;

			double rx = PCUtils.map((double) px, 0, panel.getWidth(), xMin, xMax);
			int py = (int) PCUtils.map(evaluateNaive(rx), yMin, yMax, panel.getHeight(), 0);

			g2d.drawLine(px, py, px, py);
		}
	}

	public void setLimits(int xMin, int xMax, int yMin, int yMax) {
		this.xMin = xMin;
		this.xMax = xMax;
		this.yMin = yMin;
		this.yMax = yMax;
	}

	public void add(double pCoeff) {
		alCoefficients.add(pCoeff);
	}

	public int size() {
		return alCoefficients.size();
	}

	public void clear() {
		alCoefficients.clear();
	}

	public double getCoefficient(int i) {
		if (alCoefficients.size() > i) {
			return alCoefficients.get(i);
		} else {
			return 0;
		}
	}

	public double evaluateNaive(double pX) {
		double result = 0;
		for (int i = 0; i < alCoefficients.size(); i++) {
			result = result + alCoefficients.get(i) * Math.pow(pX, i);
		}
		return result;
	}

	public double evaluateHorner(double pX) {
		double result = 0;
		for (int i = alCoefficients.size() - 1; i >= 0; i--) {
			result = result * pX + alCoefficients.get(i);
		}
		return result;
	}

	@Override
	public String toString() {
		String s = " = ";
		if (!alCoefficients.isEmpty()) {
			for (int i = 0; i < alCoefficients.size(); i++) {
				s = " + " + alCoefficients.get(i) + "x^" + i + s;
			}
			return s;
		} else {
			return "";
		}

	}

	public Object[] toArray() {
		return alCoefficients.toArray();
	}

}

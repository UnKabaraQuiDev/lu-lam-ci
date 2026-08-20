public class DoubleEquationSolution extends EquationSolution {
	
	public double x1, x2;

	public DoubleEquationSolution(double a, double b, double c, double d, double x1, double x2) {
		super(a, b, c, d);
		this.x1 = x1;
		this.x2 = x2;
	}

	@Override
	public String toString() {
		return String.format("%.0f*x^2 + %.0f*x + %.0f = 0\n"+
						 "  -> ∆ = %.0f^2 - 4*%.0f*%.0f\n"+
						 "       = %.0f\n"+
						 "=> x1 = %.0f v x2 = %.0f",
						 a, b, c, b, a, c, d, x1, x2);
	}
}
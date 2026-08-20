public class UniqueEquationSolution extends EquationSolution {

	private double x0;

	public UniqueEquationSolution(double a, double b, double c, double d, double x0) {
		super(a, b, c, d);
		this.x0 = x0;
	}
	
	@Override
	public String toString() {
		return String.format("%.0f*x^2 + %.0f*x + %.0f = 0\n"+
						 "  -> ∆ = %.0f^2 - 4*%.0f*%.0f\n"+
						 "       = %.0f\n"+
						 "=> x0 = %.0f",
						 a, b, c, b, a, c, d, x0);
	}
}
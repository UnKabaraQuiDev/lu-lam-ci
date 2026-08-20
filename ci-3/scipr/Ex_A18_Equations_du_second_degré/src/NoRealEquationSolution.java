public static class NoRealEquationSolution extends EquationSolution {

	public NoRealEquationSolution(double a, double b, double c, double d) {
		super(a, b, c, d);
	}
	
	@Override
	public String toString() {
		return String.format("%.0f*x^2 + %.0f*x + %.0f = 0\n"+
						 "  -> ∆ = %.0f^2 - 4*%.0f*%.0f\n"+
						 "       = %.0f\n"+
						 "=> //",
						 a, b, c, b, a, c, d);
	}
}
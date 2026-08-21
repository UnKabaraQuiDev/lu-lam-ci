public class Cyp {

	public static double round(double value, double nearest) {
	    return Math.round(value / nearest) * nearest;
	}
	
	public static double clamp(double x, double a, double b) {
		return (x < a ? a : (x > b ? b : x));
	}
	
}
public class Ball
{

	private double radius = 13;

	public double calculateDiameter() {
		return 2*radius;	
	}

	public double calculateVolume() {
		return 4*Math.PI*Math.pow(radius, 3)/3;	
	}

	public double calculateSurface() {
		return 4*Math.PI*Math.pow(radius, 2);	
	}
	
}
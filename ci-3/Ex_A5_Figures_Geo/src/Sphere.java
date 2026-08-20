public class Sphere
{
	private double radius;
	
	public double getSurface() {
		return 4*Math.PI*Math.pow(radius, 2);
	}
	public double getVolume() {
		return 4*Math.PI*Math.pow(radius, 3)/3;
	}
	
	public double getRadius() {return radius;}
	public void setRadius(double s) {radius = s;}
}
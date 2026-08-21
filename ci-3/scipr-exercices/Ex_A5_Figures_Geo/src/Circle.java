public class Circle
{
	private double radius;
	
	public double getPerimeter() {
		return 2*Math.PI*radius;
	}
	public double getSurface() {
		return Math.PI * Math.pow(radius, 2);	
	}
	
	public double getRadius() {return radius;}
	public void setRadius(double s) {radius = s;}
}
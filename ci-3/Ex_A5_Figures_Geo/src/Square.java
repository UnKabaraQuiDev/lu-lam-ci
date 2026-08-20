public class Square
{
	private double side;
	
	public double getPerimeter() {
		return 4*side;
	}
	public double getSurface() {
		return Math.pow(side, 2);
	}
	
	public double getSide() {return side;}
	public void setside(double s) {this.side = s;}
}
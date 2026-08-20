public class Cube {
	private double side;
	
	public double getPerimeter() {
		return 12*side;
	}
	public double getSurface() {
		return 6*Math.pow(side, 2);
	}
	
	public double getSide() {return side;}
	public void setside(double s) {this.side = s;}
}
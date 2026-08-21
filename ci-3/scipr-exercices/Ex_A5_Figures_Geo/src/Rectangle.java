public class Rectangle
{
	private double width, height;
	
	public double getPerimeter() {
		return 2*(width+height);
	}
	public double getSurface() {
		return width*height;
	}
	
	public double getWidth() {return width;}
	public double getHeight() {return height;}
	public void setHeight(double d) {height = d;}
	public void setWidth(double w) {width = w;}
}
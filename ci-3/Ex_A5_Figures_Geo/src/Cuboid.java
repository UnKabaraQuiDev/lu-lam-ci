public class Cuboid
{
	private double width, height, depth;

	public Cuboid(double w, double h, double d) {
		width = w;
		height = h;
		depth = d;
	}
	
	public double getSurface() {
		return 2*(height*width+height*depth+width*depth);
	}
	public double getVolume() {
		return width*height*depth;
	}
	
	public double getWidth() {return width;}
	public double getHeight() {return height;}
	public double getDepth() {return depth;}
	public void setHeight(double d) {height = d;}
	public void setWidth(double w) {width = w;}
	public void setDepth(double d) {depth = d;}
	public void setDimensions(double w, double h, double d) {
		width = w;
		height = h;
		depth = d;
	}
}
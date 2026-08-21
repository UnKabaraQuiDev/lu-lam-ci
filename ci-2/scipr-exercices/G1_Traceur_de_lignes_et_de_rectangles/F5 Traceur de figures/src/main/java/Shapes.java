import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Shapes {

	private List<CustomShape> shapes = new ArrayList<>();

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		
		shapes.forEach(l -> l.draw(g2d));
	}
	
	public List<CustomShape> getShapes() {
		return shapes;
	}

	public void setShapes(List<CustomShape> lines) {
		this.shapes = lines;
	}

	public boolean add(CustomShape shape) {
		return shapes.add(shape);
	}

	public void clear() {
		shapes.clear();
	}

	public Iterator<CustomShape> iterator() {
		return shapes.iterator();
	}

	public CustomShape remove(int index) {
		return shapes.remove(index);
	}

	public int size() {
		return shapes.size();
	}

	public Stream<CustomShape> stream() {
		return shapes.stream();
	}

}

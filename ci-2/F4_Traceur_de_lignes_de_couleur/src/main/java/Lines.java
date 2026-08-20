import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Lines {

	private List<Line> lines = new ArrayList<>();

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);
		
		lines.forEach(l -> l.draw(g2d));
	}
	
	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public boolean add(Line arg0) {
		return lines.add(arg0);
	}

	public void clear() {
		lines.clear();
	}

	public Iterator<Line> iterator() {
		return lines.iterator();
	}

	public Line remove(int index) {
		return lines.remove(index);
	}

	public int size() {
		return lines.size();
	}

	public Stream<Line> stream() {
		return lines.stream();
	}

}

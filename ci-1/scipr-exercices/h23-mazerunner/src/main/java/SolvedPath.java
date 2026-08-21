import java.awt.Point;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SolvedPath implements Iterable<Point>, Comparable<SolvedPath> {

	protected final List<Point> points;

	public SolvedPath(List<Point> points) {
		this.points = points;
	}

	public double computeScore() {
		return IntStream.range(0, points.size() - 1).mapToDouble(i -> points.get(i).distance(points.get(i + 1))).sum();
	}

	public List<Point> getPoints() {
		return points;
	}

	@Override
	public int compareTo(SolvedPath arg0) {
		return Double.compare(computeScore(), arg0.computeScore());
	}

	@Override
	public Iterator<Point> iterator() {
		return points.iterator();
	}

	public int size() {
		return points.size();
	}

	public Point get(int arg0) {
		return points.get(arg0);
	}

	@Override
	public String toString() {
		return points.stream().map(c -> c.getX() + "," + c.getY()).collect(Collectors.joining(" > "));
	}

}

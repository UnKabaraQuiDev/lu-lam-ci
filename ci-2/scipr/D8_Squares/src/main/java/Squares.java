import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Squares implements Iterable<Square> {

	private List<Square> squares = new ArrayList<>();

	public void draw(Graphics2D g2d, DrawPanel drawPanel) {
		int height = drawPanel.getHeight(), width = drawPanel.getWidth();
		
		for (Square square : squares) {
			g2d.setColor(new Color(Math.clamp((int) (square.x/width*255), 0, 255), Math.clamp((int) (square.y/height*255), 0, 255), 0));
			g2d.fill(square);
		}
	}
	
	public List<Square> getSquares() {
		return squares;
	}

	public void setSquares(List<Square> squares) {
		this.squares = squares;
	}

	public boolean add(Square arg0) {
		return squares.add(arg0);
	}

	public void clear() {
		squares.clear();
	}

	public Square get(int arg0) {
		return squares.get(arg0);
	}

	@Override
	public Iterator<Square> iterator() {
		return squares.iterator();
	}

	public Square remove(int arg0) {
		return squares.remove(arg0);
	}

}

package damier;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import lu.pcy113.pclib.datastructure.pair.Pair;
import lu.pcy113.pclib.datastructure.pair.Pairs;

public class Checkers implements DrawPanelDrawable {

	public static final int SIDE_COUNT = 8;

	private List<Piece> pieces;

	public Checkers(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public Checkers() {
		init();
	}

	public Pair<Boolean, String> move(int srcX, int srcY, int destX, int destY) {
		srcX = Math.clamp(srcX, 0, SIDE_COUNT - 1);
		srcY = Math.clamp(srcY, 0, SIDE_COUNT - 1);
		
		Piece srcPiece = getPieceAt(srcX, srcY);
		if (srcPiece == null) {
			return Pairs.readOnly(false, "No piece at [" + srcX + "," + srcY + "]");
		}
		
		if(Math.clamp(destX, 0, SIDE_COUNT - 1) != destX || Math.clamp(destY, 0, SIDE_COUNT - 1) != destY) { // oob
			pieces.remove(srcPiece);
			return Pairs.readOnly(true, "Piece at [" + srcX + "," + srcY + "] removed");
		}
		
		Piece destPiece = getPieceAt(destX, destY);
		if (destPiece != null) {
			return Pairs.readOnly(false, "Piece at [" + destX + "," + destY + "]");
		}

		srcPiece.moveTo(destX, destY);

		return Pairs.readOnly(true, "[" + srcX + "," + srcY + "] to [" + destX + "," + destY + "]");
	}

	public void init() {
		this.pieces = new ArrayList<>();

		for (int c = 0; c < 2; c++) { // for each color
			Color color = c == 0 ? Color.BLUE : Color.RED;

			for (int r = 0; r < 3; r++) { // for each row
				for (int i = 0; i < SIDE_COUNT; i++) { // for each column
					if ((i + r - c) % 2 == 0) {
						pieces.add(new Piece(color, i, c == 1 ? SIDE_COUNT - 1 - r : r));
					}
				}
			}
		}
	}

	public Piece getPieceAt(int x, int y) {
		return pieces.stream().filter(p -> p.isAt(x, y)).findFirst().orElse(null);
	}

	@Override
	public void accept(DrawPanel panel, Graphics2D g) {
		pieces.forEach(p -> p.accept(panel, g));
	}

	public List<Piece> getPieces() {
		return pieces;
	}

}

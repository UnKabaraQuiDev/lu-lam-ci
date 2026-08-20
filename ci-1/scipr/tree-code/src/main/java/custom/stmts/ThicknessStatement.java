package custom.stmts;

public class ThicknessStatement extends Statement {

	private float thickness;

	public ThicknessStatement(Number num) {
		if (num instanceof Double l) {
			this.thickness = (float) (double) l;
		} else if (num instanceof Float i) {
			this.thickness = (float) i;
		} else {
			throw new IllegalArgumentException(num + " " + num.getClass());
		}
	}

	public ThicknessStatement(float thickness) {
		this.thickness = thickness;
	}

	public float getThickness() {
		return thickness;
	}

	@Override
	public String toString() {
		return "ThicknessStatement [thickness=" + thickness + "]";
	}

}

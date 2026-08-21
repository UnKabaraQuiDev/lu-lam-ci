import java.awt.Color;

public enum DiseaseState {

	HEALTHY(Color.BLUE), SICK(Color.RED), IMUNE(Color.GREEN), DEAD(Color.GRAY);

	private Color color;

	private DiseaseState(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

}

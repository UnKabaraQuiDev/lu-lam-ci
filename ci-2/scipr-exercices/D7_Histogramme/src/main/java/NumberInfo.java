
public class NumberInfo {

	private double value;
	private String label;

	public NumberInfo(double value, String label) {
		this.value = value;
		this.label = label;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label + ": " + value;
	}

}

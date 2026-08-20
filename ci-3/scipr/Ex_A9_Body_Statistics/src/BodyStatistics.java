public class BodyStatistics
{
	private byte age;
	private short height;
	private short weight;
	public BodyStatistics(byte age, short height, short weight) {
		this.age = age;
		this.height = height;
		this.weight = weight;
	}
	public short getNormalWeight() {
		return (short) (height - 100);
	}
	public double getIdealWeightWoman() {
		return getNormalWeight()*0.85;
	}
	public double getIdealWeightMan() {
		return getNormalWeight()*0.9;
	}
	public double getBMI() {
		return weight/Math.pow((double) height/100, 2);
	}
}
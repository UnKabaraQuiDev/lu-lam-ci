public class Cistern {
	
	private double maximumVolume = 1000, currentVolume = 0;

	public Cistern(double radius, double height) {
		maximumVolume = Math.PI*Math.pow(radius, 2)*height;
	}
	public Cistern(double volume) {
		maximumVolume = volume;
	}
	
	public void add(double volume) {
		this.currentVolume = Cyp.clamp(currentVolume + volume, 0, maximumVolume);
	}
	public void drain(double volume) {
		this.currentVolume = Cyp.clamp(currentVolume - volume, 0, maximumVolume);
	}

	public double getCurrentVolume() {return currentVolume;}
	public double getFillLevel() {
		return currentVolume/maximumVolume*100;
	}

	@Override
	public String toString() {
		return currentVolume+"/"+maximumVolume+" "+getFillLevel()+"%";
	}
	
}
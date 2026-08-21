public class Randomizer
{
	private long min = 0, max = 11;
	public Randomizer(long min, long max) {
		setLimits(min, max);
	}
	public void setLimits(long min, long max) {
		this.min = Math.min(min, max);
		this.max = Math.max(min, max);
	}
	public long getNext() {
		return (long) (Math.floor(Math.random() * (max+1-min))) + min;
	}
}
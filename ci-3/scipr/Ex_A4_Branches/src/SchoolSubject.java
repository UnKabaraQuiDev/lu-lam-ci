public class SchoolSubject
{
	private byte test1, test2, test3;
	public void setMarks(byte t1, byte t2, byte t3) {
		this.test1 = t1 >= 0 ? t1 : 0;
		this.test2 = t2 >= 0 ? t2 : 0;
		this.test3 = t3 >= 0 ? t3 : 0;
	}
	public short getSum() {
		return (short) (test1 + test2 + test3);
	}
	public int getProduct() {
		return test1 * test2 * test3;
	}
	public double getAverage() {
		return (double) getSum()/3;
	}
}
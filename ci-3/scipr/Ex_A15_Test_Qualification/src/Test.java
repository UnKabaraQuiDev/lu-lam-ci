public class Test
{
	public static enum Qualification {
		VERY_POOR,
		POOR,
		INSUFFICIENT,
		SUFFICIENT,
		GOOD,
		VERY_GOOD,
		UNKNOWN;
	}
	
	private byte mark;

	public void setRandomMark() {
		mark = (byte) Math.rint(Math.random()*60)+1;
	}

	public Qualification getQualification() {
		if(mark < 10)
			return Qualification.VERY_POOR;
		if(mark >= 10 && mark < 20)
			return Qualification.POOR;
		if(mark >= 20 && mark < 30)
			return Qualification.INSUFFICIENT;
		if(mark >= 30 && mark < 40)
			return Qualification.SUFFICIENT;
		if(mark >= 40 && mark < 50) 
			return Qualification.GOOD;
		if(mark >= 50)
			return Qualification.VERY_GOOD;
		return Qualification.UNKNOWN;
	}
}
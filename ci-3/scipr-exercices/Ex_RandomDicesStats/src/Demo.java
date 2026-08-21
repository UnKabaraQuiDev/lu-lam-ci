public class Demo {

	public double getAverageNumberOfDice() {
		long count = 0;
		int dicesCount = 1000;
		for(int i = 0; i < dicesCount; i++) {
			count += numberOfDices();
		}
		return (double) count / dicesCount;
	}

	public int numberOfDices() {
		int counter = 1;
		while((int) (Math.random()*6)+1 != 6 && counter++ < 1000);
		return counter;
	}
	
}
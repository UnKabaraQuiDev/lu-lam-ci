import java.util.Random;

public class RandomStatistics
{
	
	private long min, max, count, lowest=Long.MAX_VALUE, highest=Long.MIN_VALUE, sum;
	
	public RandomStatistics(long mi, long ma) {
		this.min = mi;
		this.max = ma;
	}

	public long getNext() {
		long rand = (long) (Math.random()*(max-min+1)+min);
		this.highest = Math.max(highest, rand);
		this.lowest = Math.min(lowest, rand);
		this.count++;
		this.sum += rand;
		return rand;
	}

	public void printSeries(long n) {
		int count = 0;
		for(long i = 0; i < n; i++) {
			System.out.printf("%d ", getNext());
			count++;
			if(count % 20 == 0 || i == n-1)
				System.out.println();
		}
	}

	public void printStatistics() {
		System.out.printf("Range: %d-%d\nGenerated range: %d-%d\nCount: %d\nSum: %d\nAverage: %d\n", min, max, lowest, highest, count, sum, lowest+(highest-lowest)/2);
	}
	
}
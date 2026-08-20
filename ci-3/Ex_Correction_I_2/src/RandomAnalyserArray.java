import java.util.ArrayList;
import java.util.List;

public class RandomAnalyserArray {
	
	private List<Integer> arrayList = new ArrayList<>();
	
	public int getALMin() {
		return arrayList.stream().min(Integer::compareTo).get();
	}
	public int getALMax() {
		return arrayList.stream().max(Integer::compareTo).get();
	}
	public double getALAverage() {
		return arrayList.stream().mapToInt(Integer::intValue).average().getAsDouble();
	}
	
	private int[] count = new int[] {20, 10, 40, 50};
	
	public int getMin() {
		// return Arrays.stream(count).min().getAsInt();
		
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < count.length; i++) {
			min = min < count[i] ? min : count[i];
		}
		return min;
	}
	
	public int getMax() {
		// return Arrays.stream(count).max().getAsInt();
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < count.length; i++) {
			max = max > count[i] ? max : count[i];
		}
		return max;
	}
	
	public double getAverage() {
		// return Arrays.stream(count).average().getAsDouble();
		
		double sum = 0;
		for (int i = 0; i < count.length; i++) {
			sum += count[i];
		}
		return sum / count.length;
	}
	
}

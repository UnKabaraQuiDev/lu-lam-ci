import java.util.ArrayList;
import java.util.List;

import lu.pcy113.pclib.PCUtils;

public class SearchPerformanceTest {
	
	public static void main(String[] args) {
		for (int l : new int[] { 1_000, 5_000, 10_000, 25_000, 50_000, 100_000, 250_000, 500_000, 1_000_000, 2_000_000 }) {
			calc(l);
		}
	}

	private static void calc(int n) {
		long min = 1, max = n * 10;

		RandomNumbers randomNumbers = new RandomNumbers(n, min, max);

		List<Long> testNumbers = new ArrayList<>();

		for (int i = 0; i < 10000; i++) {
			testNumbers.add(PCUtils.randomLongRange(min, max));
		}

		long linearNs = PCUtils.nanoTime(() -> {
			for (long num : testNumbers) {
				randomNumbers.linearSearch(num);
			}
		});
		System.out.println(n + ",Linear Search Time," + linearNs / 1e6 + ",ms");

		long binNs = PCUtils.nanoTime(() -> {
			for (long num : testNumbers) {
				randomNumbers.binarySearch(num);
			}
		});
		System.out.println(n + ",Binary Search Time," + binNs / 1e6 + ",ms");
	}

}
import java.util.Arrays;

import lu.pcy113.pclib.PCUtils;

public class RandomNumbers {
	private int n;
	private long min, max;
	private long[] numbers;

	public RandomNumbers(int n, long min, long max) {
		this.n = n;
		this.min = min;
		this.max = max;
		this.numbers = new long[n];
		fill();
	}

	public RandomNumbers() {
	}

	public void fill(int n, long min, long max) {
		this.n = n;
		this.min = min;
		this.max = max;
		this.numbers = new long[n];

		fill();
	}

	private void fill() {
		for (int i = 0; i < n; i++) {
			numbers[i] = PCUtils.randomLongRange(min, max);
		}
		Arrays.sort(numbers);
	}

	public int linearSearch(long target) {
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] == target) {
				return i;
			}
		}
		return -1;
	}

	public int binarySearch(long target) {
		if (target < numbers[0] || target > numbers[numbers.length - 1]) {
			return -1;
		}

		int left = 0, right = numbers.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (numbers[mid] == target)
				return mid;
			if (numbers[mid] < target)
				left = mid + 1;
			else
				right = mid - 1;
		}
		return -1;
	}

	public Object[] toArray() {
		return PCUtils.castObject(numbers);
	}

}

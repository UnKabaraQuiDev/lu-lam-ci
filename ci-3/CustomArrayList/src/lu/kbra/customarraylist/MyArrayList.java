package lu.kbra.customarraylist;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MyArrayList<T> {

	public static final int INITIAL_VALUE = 10;
	private T[] values;
	private int currentSize = 0;
	private int maxSize = INITIAL_VALUE;

	public MyArrayList(int size) {
		this.maxSize = size;
		createArray();
	}

	public MyArrayList() {
		createArray();
	}

	private void createArray() {
		values = (T[]) new Object[maxSize];
	}

	public void clear() {
		values = null;
		createArray();
		currentSize = 0;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public void add(T el) {
		if (currentSize >= maxSize) {
			maxSize *= 2;
			extend();
			// throw new IndexOutOfBoundsException("Array size limited to [0;" + maxSize + "[.");
		}

		values[currentSize] = el;
		currentSize++;
	}

	public void remove() {
		if (currentSize <= 0) {
			throw new IndexOutOfBoundsException("Array size cannot be negative.");
		}
		currentSize--;
	}

	public T get(int index) {
		if (currentSize < index || index < 0) {
			throw new IndexOutOfBoundsException("Array index out of bounds: [0;" + currentSize + "]");
		}

		return values[index];
	}

	public void remove(int index) {
		if (currentSize <= 0) {
			throw new IndexOutOfBoundsException("Array size cannot be negative.");
		}

		shiftLeft(index + 1);

		currentSize--;
	}

	public Object[] toArray() {
		return IntStream.range(0, currentSize).mapToObj(i -> values[i]).collect(Collectors.toList()).toArray();
	}

	public boolean contains(T value) {
		for (int i = 0; i < currentSize; i++) {
			if (Objects.equals(values[i], value)) {
				return true;
			}
		}
		return false;
	}

	public void shuffle() {
		for (int i = 0; i < currentSize; i++) {
			swap(i, (int) (Math.random() * currentSize));
		}
	}

	public T getRandomValue() {
		return get((int) (Math.random() * currentSize));
	}

	public void sort() {
		Arrays.sort(values, 0, currentSize);
	}

	public void extend() {
		T[] old = values;
		createArray();
		System.arraycopy(old, 0, values, 0, currentSize);
	}

	private void shiftLeft(int index) {
		for (int i = index; i < maxSize; i++) {
			values[i - 1] = values[i];
		}
	}

	private void swap(int i, int j) {
		T temp = values[j];
		values[j] = values[i];
		values[i] = temp;
	}

	@Override
	public String toString() {
		return "[" + IntStream.range(0, currentSize).mapToObj(i -> Objects.toString(values[i])).collect(Collectors.joining(", ")) + "]";
	}

}

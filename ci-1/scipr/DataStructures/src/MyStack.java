import java.util.Arrays;
import java.util.stream.Collectors;

public class MyStack {

	private int[] arr;
	private int size = 0;

	public MyStack() {
	}

	public MyStack(int initSize) {
		this.arr = new int[initSize];
	}

	public void push(int val) {
		size++;
		if (arr == null) {
			arr = new int[size];
		} else if (arr.length < size) {
			final int[] arr1 = new int[size];
			System.arraycopy(arr, 0, arr1, 0, arr.length);
			arr = arr1;
		}

		arr[size - 1] = val;
	}

	public int pop() {
		if (size == 0)
			throw new IllegalArgumentException("bla bla");
		size--;
		return arr[size];
	}

	public int size() {
		return size;
	}

	public int allocSize() {
		return arr == null ? 0 : arr.length;
	}

	@Override
	public String toString() {
		return "[" + Arrays.stream(arr).limit(size).mapToObj(Integer::toString).collect(Collectors.joining(", ")) + "] size: " + size
				+ " length: " + arr.length;
	}

	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);

		System.out.println(stack);

		stack.push(5);

		System.out.println(stack);

		System.out.println(stack.pop());
		System.out.println(stack.pop());

		System.out.println(stack);

		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		System.out.println(stack);
	}
}

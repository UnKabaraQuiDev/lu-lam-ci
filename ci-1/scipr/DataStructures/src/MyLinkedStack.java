import java.util.Arrays;
import java.util.stream.Collectors;

public class MyLinkedStack {

	private Node root;
	private int size;

	public MyLinkedStack() {
	}

	public void push(int val) {
		size++;
		Node newNode = new Node(val);
		newNode.setNext(root);
		this.root = newNode;
	}

	public int pop() {
		if (size == 0)
			throw new IllegalArgumentException("bla bla");
		size--;
		int val = root.getData();
		root = root.getNext();
		return val;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return root == null ? "empty" : root.toString();
	}

	public static void main(String[] args) {
		final MyLinkedStack stack = new MyLinkedStack();
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

public class MyLinkedQueue {

	private Node root, last;
	private int size;

	public MyLinkedQueue() {
	}

	public void push(int val) {
		size++;
		Node newNode = new Node(val);
		newNode.setNext(root);
		this.root = newNode;
		if (last == null)
			last = root;
	}

	public int pop() {
		if (size == 0) {
			throw new IllegalStateException("Queue is empty");
		}

		int value;

		if (root == last) {
			value = root.getData();
			root = null;
			last = null;
		} else {
			Node current = root;
			while (current.getNext() != last) {
				current = current.getNext();
			}
			value = last.getData();
			last = current;
			last.setNext(null);
		}

		size--;
		return value;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return root == null ? "empty" : root.toString();
	}

	public static void main(String[] args) {
		final MyLinkedQueue stack = new MyLinkedQueue();
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

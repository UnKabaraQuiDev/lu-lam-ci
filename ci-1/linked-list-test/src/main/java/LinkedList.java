import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class LinkedList implements Iterable<Integer> {

	public static void main(String[] args) {
		final LinkedList ll = new LinkedList();

		ll.add(1);
		assert ll.size == 1;
		ll.add(2);
		assert ll.size == 2;
		ll.add(3);
		assert ll.size == 3;

		System.out.println(ll);

		assert ll.get(0) == 1;
		assert ll.get(1) == 2;
		assert ll.get(2) == 3;

		assert ll.remove(1) == 2;
		assert ll.remove(1) == 3;
		assert ll.remove(0) == 1;

		System.out.println(ll);

		ll.add(1);
		assert ll.size == 1;
		ll.add(2);
		assert ll.size == 2;
		ll.add(3);
		assert ll.size == 3;

		System.out.println(ll);

		ll.swap(0, 2);

		System.out.println(ll);

		assert ll.get(0) == 3;
		assert ll.get(1) == 2;
		assert ll.get(2) == 1;

		System.out.println(Arrays.toString(ll.toArray()));

		ll.set(0, 10);
		ll.set(1, 5);
		ll.set(ll.size, 12);

		assert ll.get(1) == 5;

		System.out.println(ll);

		assert ll.verifySort() == false;

		ll.bubbleSort();

		System.out.println(ll);

		assert ll.verifySort();

		ll.clear();

		ll.add(10);
		ll.add(1);
		ll.add(9);
		ll.add(19);
		ll.add(69);
		ll.add(16);
		ll.add(8);

		System.out.println(ll);

		assert ll.verifySort() == false;

		ll.selectionSort();

		System.out.println(ll);

		assert ll.verifySort();
	}

	public class Node {

		private int value;
		private Node next;

		public Node(int value, LinkedList.Node next) {
			this.value = value;
			this.next = next;
		}

		public Node(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return value + (next != null ? ", " + next.toString() : "");
		}

	}

	private Node root;
	private int size = 0;

	public void add(int value) {
		addNode(new Node(value));
	}

	private void addNode(LinkedList.Node node) {
		if (root == null) {
			this.root = node;
			size++;
			return;
		}

		Node tmp = root;
		while (tmp.getNext() != null) {
			tmp = tmp.getNext();
		}

		tmp.setNext(node);
		size++;
	}

	public int get(int idx) {
		return getNode(idx).getValue();
	}

	private LinkedList.Node getNode(int idx) {
		if (idx > size - 1) {
			throw new IndexOutOfBoundsException(idx + " is out of bounds of size " + size);
		}
		if (idx < 0) {
			throw new IndexOutOfBoundsException(idx + " can't be negative");
		}

		if (idx == 0) {
			return root;
		}

		Node target = root;
		while (idx > 0) {
			target = target.getNext();
			idx--;
		}

		return target;
	}

	public int remove(int idx) {
		return removeNode(idx).getValue();
	}

	private LinkedList.Node removeNode(int idx) {
		if (idx > size - 1) {
			throw new IndexOutOfBoundsException(idx + " is out of bounds of size " + size);
		}

		if (idx == 0) {
			final Node tmp = root;
			root = root.getNext();
			size--;
			return tmp;
		}

		final Node prev = getNode(idx - 1);
		final Node target = prev.getNext(); // cannot be null
		final Node afterNext = target.getNext(); // could be null but we don't care
		prev.setNext(afterNext);

		size--;

		return target;
	}

	public void swap(int idx0, int idx1) {
		if (idx0 > size - 1) {
			throw new IndexOutOfBoundsException(idx0 + " is out of bounds of size " + size);
		}
		if (idx1 > size - 1) {
			throw new IndexOutOfBoundsException(idx1 + " is out of bounds of size " + size);
		}
		if (idx0 < 0) {
			throw new IndexOutOfBoundsException(idx0 + " can't be negative");
		}
		if (idx1 < 0) {
			throw new IndexOutOfBoundsException(idx0 + " can't be negative");
		}
		if (idx0 == idx1) {
			return;
		}

		final int min = Math.min(idx0, idx1);
		final int max = Math.max(idx0, idx1);

		final Node node0 = getNode(min);
		final Node node1 = getNode(max);

		final int tmp = node0.getValue();
		node0.setValue(node1.getValue());
		node1.setValue(tmp);
	}

	public void set(int idx, int val) {
		setNode(idx, new Node(val));
	}

	public void setNode(int idx, Node node) {
		if (idx > size) {
			throw new IndexOutOfBoundsException(idx + " is out of bounds of size " + size);
		}
		if (idx < 0) {
			throw new IndexOutOfBoundsException(idx + " can't be negative");
		}

		if (idx == 0) {
			node.setNext(root);
			this.root = node;
			size++;
			return;
		}

		final Node prevNode = getNode(idx - 1);
		final Node potReplaceNode = prevNode.getNext(); // could be null but we don't care

		prevNode.setNext(node);
		node.setNext(potReplaceNode);
		size++;
	}

	public int size() {
		return size;
	}

	public Integer[] toArray() {
		final List<Integer> ints = new ArrayList<>();
		iterator().forEachRemaining(ints::add);
		return ints.toArray(new Integer[0]);
	}

	public boolean verifySort() {
		if (size == 0)
			return true;

		int last = root.getValue();
		final Iterator<Integer> its = iterator();
		while (its.hasNext()) {
			if (last > (last = its.next())) {
				return false;
			}
		}

		return true;
	}

	// bubble sort
	public void bubbleSort() {
		if (size <= 1) {
			return;
		}
		if (verifySort()) {
			return;
		}

		for (int i = 0; i < size - 1; i++) {
			for (int j = 0; j < size - 1 - i; j++) {
				final Node node0 = getNode(j);
				final Node node1 = node0.getNext(); // can't be null

				if (node0.value > node1.value) {
					final int tmp = node0.getValue();
					node0.setValue(node1.getValue());
					node1.setValue(tmp);
				}
			}
		}
	}

	public void selectionSort() {
		if (size <= 1) {
			return;
		}
		if (verifySort()) {
			return;
		}

		for (Node c = root; c != null; c = c.next) {
			Node min = c;
			for (Node j = c.next; j != null; j = j.next) {
				if (j.value < min.value) {
					min = j;
				}
			}

			int tmp = c.value;
			c.value = min.value;
			min.value = tmp;
		}
	}

	public void clear() {
		this.root = null;
		size = 0;
	}

	@Override
	public String toString() {
		return "(length = " + size + ")[" + (root == null ? "" : root.toString()) + "]";
	}

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {
			private Node current = LinkedList.this.root;

			@Override
			public Integer next() {
				final int tmp = current.getValue();
				current = current.getNext();
				return tmp;
			}

			@Override
			public boolean hasNext() {
				return current != null;
			}

		};
	}

}

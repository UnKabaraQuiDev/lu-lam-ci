public class MyLinkedList {

	private Node root, last;
	private int size;

	public MyLinkedList() {
	}

	public MyLinkedList(Node root) {
		this.root = root;
	}

	public void add(int val) {
		size++;

		final Node newNode = new Node(val);

		if (root == null) {
			last = root = newNode;
			return;
		}

		last.setNext(newNode);
		last = newNode;
	}

	public int remove(int idx) {
		if (idx == 0) {
			int data = root.getData();
			root = root.getNext();
			return data;
		}

		Node prev = root;

		while (prev != null && idx > 1) {
			prev = prev.getNext();
			idx--;
		}

		if (prev == null || prev.getNext() == null) {
			throw new IndexOutOfBoundsException("idx too big");
		}

		Node toRemove = prev.getNext();
		if (toRemove == last)
			last = prev;

		int data = toRemove.getData();
		prev.setNext(toRemove.getNext());

		size--;

		return data;
	}

	public int get(int idx) {
		Node n = root;
		while (n != null && idx != 0) {
			idx--;
			n = n.getNext();
		}

		if (idx != 0 || n == null) {
			throw new IndexOutOfBoundsException("idx to big");
		}

		return n.getData();
	}

	public void clear() {
		size = 0;
		this.root = null;
		this.last = null;
	}

	public int indexOf(int value) {
		Node tmp = root;
		int i = 0;
		while (tmp != null && tmp.getData() != value) {
			tmp = tmp.getNext();
			i++;
		}
		if (tmp == null)
			return -1;
		return i;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public boolean verifySort() {
		for (int i = 0; i < size - 1; i++) {
			int posmin = i;
			for (int j = i + 1; j < size; j++)
				if (get(j) < get(posmin))
					return false;
		}
		return true;
	}

	public void reverse() {
		for (int i = 0; i < size / 2; i++) {
			int temp = get(i);
			set(i, get(size - i - 1));
			set(size - i - 1, temp);

			System.out.println("--");
			print();
		}
	}

	public void set(int data, int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds");
		} else {
			Node tmp = root;
			for (int i = 0; i < index; i++) {
				tmp = tmp.getNext();
			}
			tmp.setData(data);
		}
	}

	public boolean contains(int value) {
		return indexOf(value) != -1;
	}

	public void selectionSort() {
		if (root == null || root.getNext() == null) {
			return;
		}

		Node current = root;

		while (current != null) {
			Node minNode = current;
			Node next = current.getNext();

			while (next != null) {
				if (next.getData() < minNode.getData()) {
					minNode = next;
				}
				next = next.getNext();
			}

			int temp = current.getData();
			current.setData(minNode.getData());
			minNode.setData(temp);

			current = current.getNext();
		}
	}

	public int size() {
		return size;
	}

	public void print() {
		if (root == null) {
			return;
		}

		Node node = root;
		while (node != null) {
			System.out.println(node.getData());
			node = node.getNext();
		}
	}

	@Override
	public String toString() {
		return root == null ? "[]" : "[" + root.toString() + "]";
	}

	public static void main(String[] args) {
		final MyLinkedList m = new MyLinkedList();

		m.add(1);
		m.add(2);
		m.add(3);

		m.print();
		System.out.println(m.toString());

		System.out.println(m.size());

		System.out.println(m.get(0));
		System.out.println(m.get(1));
		System.out.println(m.get(2));

		m.remove(0);
		System.out.println(m.toString());

		m.add(7);
		m.add(8);
		m.add(9);

		System.out.println(m.toString());
		System.out.println(m.indexOf(8));
		System.out.println(m.indexOf(10));

		System.out.println(m.contains(9));
		System.out.println(m.contains(12));
	}

}

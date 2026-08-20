import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lu.pcy113.pclib.PCUtils;

public class SortDemo {

	public static void main(String[] args) {
		final List<Integer> list = new ArrayList<>();
		for (int i : PCUtils.randomIntArray(15, 0, 100)) {
			list.add(i);
		}
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
	}

}

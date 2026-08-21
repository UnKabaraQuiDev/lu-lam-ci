package lu.kbra.random.picker.gui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Activities {

	private List<Activity> activities = new ArrayList<>();

	public List<Activity> getActivities() {
		return activities;
	}

	public void setActivities(List<Activity> activities) {
		this.activities = activities;
	}

	public Object[] toArray() {
		return activities.toArray();
	}

	public boolean add(Activity e) {
		return activities.add(e);
	}

	public int size() {
		return activities.size();
	}

	public void clear() {
		activities.clear();
	}

	public Activity get(int i) {
		return activities.get(i);
	}

	public Activity remove(int i) {
		return activities.remove(i);
	}

	public Stream<Activity> stream() {
		return activities.stream();
	}

	public void forEach(Consumer<? super Activity> action) {
		activities.forEach(action);
	}

	public void shuffle() {
		Collections.shuffle(activities);
	}
	
	public int getRandomIndex() {
		return (int) (Math.random()*size());
	}
	
	public void shuffle2() {
		for(int i = 0; i < 1000; i++) {
			int index1 = getRandomIndex(),
				index2 = getRandomIndex();
			
			Activity a1 = get(index1);
			Activity a2 = get(index2);
			
			activities.set(index2, a1);
			activities.set(index1, a2);
		}
	}

}

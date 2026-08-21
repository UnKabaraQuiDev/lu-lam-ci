package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class PersonList implements Iterable<Person> {

	protected final ArrayList<Person> alPersons;

	public PersonList() {
		alPersons = new ArrayList<>();
	}

	public void sort() {
		Collections.sort(alPersons);
	}

	public Person getPerson(int index) {
		return alPersons.get(index);
	}

	public void removeIndex(int index) {
		alPersons.remove(index);
	}

//	@Deprecated
	public void add(Person p) {
		alPersons.add(p);
	}

	public int size() {
		return alPersons.size();
	}

	@Override
	public Iterator<Person> iterator() {
		return alPersons.iterator();
	}

	@Override
	public String toString() {
		return "PersonList [alPersons=" + alPersons + "]";
	}

}

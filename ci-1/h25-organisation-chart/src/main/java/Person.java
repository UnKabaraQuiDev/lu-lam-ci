import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;

public class Person implements Comparable<Person> {

	private final String firstName;
	private final String surname;
	private final String title;

	private Person parent;
	private final SortedSet<Person> subordinates;

	public Person(String firstName, String surname, String title) {
		this.firstName = firstName;
		this.surname = surname;
		this.title = title;
		this.subordinates = new TreeSet<>();
	}

	public Person(String firstName, String surname, String title, Person parent) {
		this.firstName = firstName;
		this.surname = surname;
		this.title = title;
		this.subordinates = new TreeSet<>();

		this.parent = parent;
		parent.getSubordinates().add(this);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSurname() {
		return surname;
	}

	public String getTitle() {
		return title;
	}

	public Person getParent() {
		return parent;
	}

	public SortedSet<Person> getSubordinates() {
		return subordinates;
	}

	public void addSubordinate(Person worker) {
		Objects.requireNonNull(worker);
		worker.parent = this;
		subordinates.add(worker);
	}

	public void removeSubordinate(Person worker) {
		if (subordinates.remove(worker)) {
			worker.parent = null;
		}
	}

	@Override
	public int compareTo(Person other) {
		final int bySurname = this.surname.compareTo(other.surname);
		if (bySurname != 0) {
			return bySurname;
		}
		return this.firstName.compareTo(other.firstName);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Person)) {
			return false;
		}
		final Person person = (Person) o;
		return firstName.equals(person.firstName) && surname.equals(person.surname) && title.equals(person.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, surname, title);
	}
}
package model;

public interface DataSource {

	void load(PersonList pl);
	
	void save(PersonList pl);
	
	void update(Person p);
	
	Person insert(PersonList pl);
	
}

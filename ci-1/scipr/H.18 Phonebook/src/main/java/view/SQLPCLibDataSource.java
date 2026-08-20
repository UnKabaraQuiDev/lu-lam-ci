package view;

import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseConnector;

import model.DataSource;
import model.Person;
import model.PersonList;

public class SQLPCLibDataSource implements DataSource {

	protected DataBase db;
	protected PersonTable people;

	public SQLPCLibDataSource() {
		db = new DataBase(new DataBaseConnector("user", "pass", "localhost", 3306), "scipr_h18");
		db.create().thenConsume(System.out::println).run();
		people = new PersonTable(db);
		people.create().thenConsume(System.out::println).run();
	}

	@Override
	public void load(PersonList pl) {
		people.all().forEachRemaining(pl::add);
	}

	@Override
	public void save(PersonList pl) {
		pl.forEach(p -> people.updateAndReload(p).run());
	}

	@Override
	public void update(Person p) {
		people.updateAndReload(p).run();
	}

	@Override
	public Person insert(PersonList pl) {
		final Person p = people.insertAndReload(new Person()).run();
		pl.add(p);
		return p;
	}

}

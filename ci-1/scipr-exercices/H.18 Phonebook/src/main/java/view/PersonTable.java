package view;

import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;
import lu.pcy113.pclib.db.loader.BufferedPagedEnumeration;
import lu.pcy113.pclib.db.loader.PagedEnumeration;

import model.Person;

public class PersonTable extends DataBaseTable<Person> {

	public PersonTable(DataBase dataBase) {
		super(dataBase);
	}

	public PagedEnumeration<Person> all() {
		return new BufferedPagedEnumeration<>(20, this);
	}

}

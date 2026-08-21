package model;

import lu.pcy113.pclib.db.autobuild.column.AutoIncrement;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.Nullable;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class Person implements Comparable<Person>, DataBaseEntry {

	@PrimaryKey
	@AutoIncrement
	@Column
	protected int id;
	@Column
	@Nullable
	protected String name;
	@Column
	@Nullable
//	@Unique
	protected String email;
	@Column
	@Nullable
//	@Unique
	protected String phone;

	public Person() {
	}

	public Person(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Person(int id, String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public String toFile() {
		return name + ";" + email + ";" + phone;
	}

	@Override
	public int compareTo(Person p) {
		return name != null ? p.name == null ? 0 : name.compareTo(p.name) : 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]";
	}

}

package lu.kbra.random.picker.gui;

import java.time.Instant;
import java.util.Date;

public class Activity {

	private String name;
	private Date creationDate;

	public Activity(String name) {
		this.name = name;
		this.creationDate = Date.from(Instant.now());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

}

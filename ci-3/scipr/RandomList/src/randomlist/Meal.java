package randomlist;

public class Meal {

	private double priceStudent; // in Euros
	private double priceTeacher; // in Euros
	private String type; // Starter, Main, Dessert, Salad, Drink
	private boolean isVegan;
	private boolean isVegetarian;
	private boolean containsPork;
	private String name;

	public Meal(double priceStudent, double priceTeacher, String type, boolean isVegan, boolean isVegetarian, boolean containsPork, String name) {
		this.priceStudent = priceStudent;
		this.priceTeacher = priceTeacher;
		this.type = type;
		this.isVegan = isVegan;
		this.isVegetarian = isVegetarian;
		this.containsPork = containsPork;
		this.name = name;
	}

	public double getPriceStudent() {
		return priceStudent;
	}

	public void setPriceStudent(double priceStudent) {
		this.priceStudent = priceStudent;
	}

	public double getPriceTeacher() {
		return priceTeacher;
	}

	public void setPriceTeacher(double priceTeacher) {
		this.priceTeacher = priceTeacher;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isIsVegan() {
		return isVegan;
	}

	public void setIsVegan(boolean isVegan) {
		this.isVegan = isVegan;
	}

	public boolean isIsVegetarian() {
		return isVegetarian;
	}

	public void setIsVegetarian(boolean isVegetarian) {
		this.isVegetarian = isVegetarian;
	}

	public boolean isContainsPork() {
		return containsPork;
	}

	public void setContainsPork(boolean containsPork) {
		this.containsPork = containsPork;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.name+" - "+priceStudent+"€ ("+priceTeacher+"€)";
	}

}

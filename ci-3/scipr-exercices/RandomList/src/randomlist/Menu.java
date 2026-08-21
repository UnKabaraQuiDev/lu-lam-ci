package randomlist;

import java.util.ArrayList;
import java.util.List;


public class Menu {
	
	private List<Meal> meals;

	public Menu(List<Meal> meals) {
		this.meals = meals;
	}

	public Menu() {
		meals = new ArrayList<>();
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public Object[] toArray() {
		return meals.toArray();
	}

	public boolean add(Meal e) {
		return meals.add(e);
	}

	public void clear() {
		meals.clear();
	}

	public Meal get(int i) {
		return meals.get(i);
	}

	public Meal set(int i, Meal e) {
		return meals.set(i, e);
	}

	public void add(int i, Meal e) {
		meals.add(i, e);
	}

	public Meal remove(int i) {
		return meals.remove(i);
	}

}

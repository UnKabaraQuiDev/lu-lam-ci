
public class Main {

	public static class Animal {
		public void makeSound() {
			System.out.println("Make sound.");
		}
	}

	public static class Dog extends Animal {
		@Override
		public void makeSound() {
			System.out.println("Dog override.");
		}
	}

	public static class NotDog extends Animal {
		public void makeSound() {
			System.out.println("Dog not override.");
		}
	}

	public static void main(String[] args) {
		Animal animal = new Dog();
		animal.makeSound();
		
		Animal notAnimal = new NotDog();
		((Animal) notAnimal).makeSound();
	}

}

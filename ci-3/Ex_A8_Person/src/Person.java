public class Person
{
	private String surName;
	private String givenName;
	public Person(String given, String sur) {
		this.surName = sur;
		this.givenName = given;
	}
	@Override
	public String toString() {
		return surName+" "+givenName;
	}
	public void sayHello() {
		System.out.println("Hello, my name is "+surName+" "+giveName);
	}
}
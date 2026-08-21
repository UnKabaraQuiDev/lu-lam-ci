public class Account
{
	private double balance;
	
	public void deposit(double sum) {
		balance += sum;
	}
	public void withdraw(double sum) {
		balance -= sum;
	}
	
	public double getBalance() {
		return balance;
	}
 }
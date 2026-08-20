package b.atm;

public class ATMData {

	private int eur50Available, eur10Available;
	private int eur50Transaction, eur10Transaction;

	public void fillATM(int eur50, int eur10) {
		this.eur10Available = eur10;
		this.eur50Available = eur50;
	}

	public int withdrawAmount(int amount) {
		if (amount % 10 != 0) {
			return 1;
		}
		
		int c50 = amount / 50;
		int c10 = (amount-c50*50)/10;
		
		if(c50 > eur50Available || c10 > eur10Available)
			return 2;
		
		eur50Available -= c50;
		eur10Available -= c10;
		
		eur50Transaction = c50;
		eur10Transaction = c10;
		
		return 0;
	}

	public int getEur50Available() {
		return eur50Available;
	}

	public void setEur50Available(int eur50Available) {
		this.eur50Available = eur50Available;
	}

	public int getEur10Available() {
		return eur10Available;
	}

	public void setEur10Available(int eur10Available) {
		this.eur10Available = eur10Available;
	}

	public int getEur50Transaction() {
		return eur50Transaction;
	}

	public int getEur10Transaction() {
		return eur10Transaction;
	}

}

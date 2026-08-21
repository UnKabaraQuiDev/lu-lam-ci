public class DnD
{

	public boolean throwCoin() {
		return throwDMinMax(0, 1) == 1;	
	}
	public int throwD6() {
		return throwDMinMax(1, 6);
	}
	public int throwD20() {
		return throwDMinMax(1, 20);	
	}
	public int throwDn(int n) {
		return throwDMinMax(1, n);
	}
	public int throwDNWithStart(int n, int min) {
		return throwDMinMax(min, min+n);	
	}
	public int throwDMinMax(int min, int max) {
		return (int) (Math.random()*(max-min+1)+min);
	}
	public int throwD100() {
		return throwDMinMax(0, 9)*10;
	}
	public int throwTwoD20() {
		return Math.max(throwDMinMax(1, 20), throwDMinMax(1, 20));	
	}
	
}
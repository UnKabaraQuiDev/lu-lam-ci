public class Structotest
{
	private double a, b, c;

	public void setAll(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	public double checkSA() {
		if(checkS())
			return Math.sqrt(a*b*c);
		else
			if(a*b > 0)
				return Math.sqrt(a*b);
			else
				if(b*c > 0)
					return Math.sqrt(b*c);
				else
					if(a*c > 0)
						return Math.sqrt(a*c);
					else
						return -1;
	}

	public boolean chestB() {
		if(a != 0 && b != 0 && c != 0)
			if(a*a+b*b == c*c)
				return true;
			else
				return false;
		else {
			if(c == 0)
				c = Math.sqrt(a*a+b*b);
			else
				if(b == 0)
					b = Math.sqrt(c*c-a*a);
				else
					if(a == 0)
						a = Math.sqrt(c*c-b*b);
		}
		return true;
	}

	public boolean checkS() {
		if(a == 0 || b == 0 || c == 0)
			return true;
		else
			if(a > 0)
				if(b > 0) 
					if(c > 0)
						return true;
					else 
						return false;
				else
					if(c > 0)
						return false;
					else
						return true;
			else
				if(b >= 0)
					if(c > 0)
						return false;
					else
						return true;
				else
					if(c > 0)
						return true;
					else
						return false;
	}
}
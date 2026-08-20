public class AnalyseDate
{
	private byte day, month;
	private short year;

	public AnalyseDate(byte day, byte month, short year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public boolean isLeapYear() {
		return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
	}

	private static final byte[] lookup = new byte[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

	
	public boolean isValid2() {
		return month > 0 && month < 12
			&& day > 0 && (day <= lookup[month] && (month == 2 && day == 29 ? isLeapYear() : true));
	}
	
	public boolean isValid1() {
		if(day <= 0)
			return false;
			
		switch(month) {
		case 1:
			return day <= 31;
		case 2:
			return day == 29 ? isLeapYear() : day <= 28;
		case 3:
			return day <= 31;
		case 4:
			return day <= 30;
		case 5:
			return day <= 31;
		case 6:
			return day <= 30;
		case 7:
			return day <= 31;
		case 8:
			return day <= 31;
		case 9:
			return day <= 30;
		case 10:
			return day <= 31;
		case 11:
			return day <= 30;
		case 12:
			return day <= 31;
		default:
			return false;
		}
	}

	public byte getDay() {return day;}
	public byte getMonth() {return month;}
	public short getYear() {return year;}

	@Override
	public String toString() {
		return String.format("%d.%d.%d", day, month, year);
	}
	
}
public class BodyStatistics
{
	public static enum Sex {
		M, F;
	}
	
	private byte age;
	private short height; // cm
	private short weight;
	private Sex sex;

	public BodyStatistics(byte age, short height, short weight, Sex sex) {
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
	}

	public double getNormalWeight() {
		return (double) height/100 -100;
	}

	public double getIdealWeight() {
		switch(sex) {
		case M:
			return getNormalWeight()*0.9;
		case F:
			return getNormalWeight()*0.85;
		}
		return -1;
	}

	public double getBMI() {
		return (double) weight/Math.pow(height, 2)+getBMIAdaptation();
	}

	public byte getBMIAdaptation() {
		if(age >= 19 && age <= 24)
			return -2;
		if(age >= 25 && age <= 34)
			return -1;
		if(age >= 35 && age <= 44)
			return -0;
		if(age >= 45 && age <= 54)
			return -1;
		if(age >= 55 && age <= 64)
			return -2;
		if(age > 64)
			return 3;
		return 0;
	}

	public String getComment() {
		double bmi = getBMI();
		switch(sex) {
		case M:
			if(bmi < 20.7)
				return "Maigreur";
			if(bmi >= 20.7 && bmi < 26.4)
				return "Idéal";
			if(bmi >= 26.4 && bmi < 27.8)
				return "A la limite du surpoids";
			if(bmi >= 27.8 && bmi < 31.1)
				return "Surpoids";
			if(bmi >= 31.1)
				return "Obésité";
		case F:
			if(bmi < 20.7)
				return "Maigreur";
			if(bmi >= 20.7 && bmi < 26.4)
				return "Idéal";
			if(bmi >= 26.4 && bmi < 27.8)
				return "A la limite du surpoids";
			if(bmi >= 27.8 && bmi < 31.1)
				return "Surpoids";
			if(bmi >= 31.1)
				return "Obésité";
		}
		return null;
	}
	
}
public class Fraction {
    
    private int numerator, denominator;

    public Fraction(int num, int denum) {
        this.numerator = num;
        this.denominator = denum;
    }

    public int getNumerator() {return numerator;}
    public int getDenominator() {return denominator;}
    public double getDecimal() {return (double) numerator/denominator;}

    @Override
    public String toString() {
        return numerator+"/"+denominator+" ("+getDecimal()+")";
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public int gcd() {
        return gcd(Math.abs(numerator), Math.abs(denominator));
    }

    public void simplify() {
        int gcd = gcd();
        numerator /= gcd;
        denominator /= gcd;
    }

        public void add(Fraction other) {
        int newNumerator = (this.numerator * other.denominator) + (other.numerator * this.denominator);
        int newDenominator = this.denominator * other.denominator;
        this.numerator = newNumerator;
        this.denominator = newDenominator;
        simplify();
    }

    public void subtract(Fraction other) {
        int newNumerator = (this.numerator * other.denominator) - (other.numerator * this.denominator);
        int newDenominator = this.denominator * other.denominator;
        this.numerator = newNumerator;
        this.denominator = newDenominator;
        simplify();
    }

    public void multiply(Fraction other) {
        this.numerator *= other.numerator;
        this.denominator *= other.denominator;
        simplify();
    }

    public void divide(Fraction other) {
        this.numerator *= other.denominator;
        this.denominator *= other.numerator;
        simplify();
    }

}
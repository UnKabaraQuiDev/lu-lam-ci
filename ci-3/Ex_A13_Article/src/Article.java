public class Article
{
	private double unitPrice;
	// char => unsigned short; 2B: 65_535
	private char quantity;

	public Article(double unit, char quant){
		this.unitPrice = unit;
		this.quantity = quant;
	}

	public double getTotalPrice() {
		return unitPrice*quantity*1.15*(quantity > 20 ? 0.9 : 1);
	}
}
public class Test {
	
	private int mark;

	public Test(int mark) {
		this.mark = clamp(mark, 1, 60);
	}

	public String getEvaluation() {
		return mark >= 30 ? "test passed" : "test failed";
	}

	private int clamp(int x, int a, int b) {
		return (x < a ? a : (x > b ? b : x));
	}
	
	public int getMark() {return mark;}
	//public void setMark(int mark) {this.mark = mark;}

}
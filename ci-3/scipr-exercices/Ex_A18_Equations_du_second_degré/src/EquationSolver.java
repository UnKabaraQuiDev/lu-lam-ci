import java.lang.RuntimeException;
import java.util.List;
import java.util.ArrayList;

public class EquationSolver
{
	
	private List<EquationSolution> history = new ArrayList<>();
	
	private double a, b, c;

	// a*x^2 + b*x + c = 0
	public EquationSolver(double a, double b, double c) {
		this.setAttributes(a, b, c);
	}
	
	public EquationSolution solve() {
		if(a == 0)
			throw new EquationException("a == 0.");

		double delta = Math.pow(b, 2)-4*a*c;
		
		if(delta < 0)
			return appendHistory(new NoRealEquationSolution(a, b, c, delta));
	
		if(delta == 0)
			return appendHistory(new UniqueEquationSolution(a, b, c, delta, (double) -(b/2*a)));

		if(delta > 0)
			return appendHistory(new DoubleEquationSolution(a, b, c, delta, (double) (-b-Math.sqrt(delta))/2*a, (double) (-b+Math.sqrt(delta))/2*a));

		return null;
	}

	public void showSolution() {
		if(history != null && history.size() > 0) {
			System.out.println(history.get(history.size()-1));
		}else {
			System.out.println("Aucune equation calculée.");
		}
	}

	public void showHistory() {
		int j = 0;
		for(EquationSolution i : history) {
			System.out.println((j++)+": --- \n"+i);
		}
	}
	public List<EquationSolution> getHistory() {
		return history;
	}

	protected EquationSolution appendHistory(EquationSolution es) {
		this.history.add(es);
		return es;
	}
	
	public void setAttributes(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
}
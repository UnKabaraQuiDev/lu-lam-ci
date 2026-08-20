import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	public MainFrame() {
		setLayout(new BorderLayout());

		final Person ceo = new Person("First", "Surname", "CEO");

		final Person e1 = new Person("Dani", "Fox", "E1");
		final Person e2 = new Person("Quinn", "Baker", "E2");
		final Person e3 = new Person("Ava", "Voss", "E3");
		final Person e4 = new Person("Ann", "Vidal", "E4");
		final Person e5 = new Person("Abel", "Vega", "E5");
		final Person e6 = new Person("Aya", "Vera", "E6");

		final Person e7 = new Person("Liam", "Stone", "E7");
		final Person e8 = new Person("Mila", "Klein", "E8");
		final Person e9 = new Person("Noah", "Young", "E9");
		final Person e10 = new Person("Emma", "Parks", "E10");
		final Person e11 = new Person("Owen", "Hill", "E11");
		final Person e12 = new Person("Ivy", "Reed", "E12");

		final Person e13 = new Person("Leo", "Smith", "E13");
		final Person e14 = new Person("Zoe", "Mills", "E14");
		final Person e15 = new Person("Nina", "Cole", "E15");
		final Person e16 = new Person("Aron", "Page", "E16");
		final Person e17 = new Person("Mason", "Gray", "E17");
		final Person e18 = new Person("Ella", "King", "E18");

		final Person e19 = new Person("Finn", "Clark", "E19");
		final Person e20 = new Person("Sara", "Wells", "E20");
		final Person e21 = new Person("Jack", "Ward", "E21");
		final Person e22 = new Person("Ruby", "Lane", "E22");
		final Person e23 = new Person("Hugo", "Ross", "E23");
		final Person e24 = new Person("Mia", "Hart", "E24");

		final Person e25 = new Person("Theo", "Price", "E25");
		final Person e26 = new Person("Lena", "Nash", "E26");
		final Person e27 = new Person("Eli", "Ford", "E27");
		final Person e28 = new Person("Jade", "Holt", "E28");
		final Person e29 = new Person("Kai", "Brooks", "E29");
		final Person e30 = new Person("Pia", "Turner", "E30");
		final Person e31 = new Person("Remy", "Moore", "E31");
		final Person e32 = new Person("Skye", "Dunn", "E32");

		ceo.addSubordinate(e1);
		ceo.addSubordinate(e2);
		ceo.addSubordinate(e7);
		ceo.addSubordinate(e8);

		e1.addSubordinate(e5);
		e1.addSubordinate(e9);
		e1.addSubordinate(e10);

		e2.addSubordinate(e3);
		e2.addSubordinate(e4);
		e2.addSubordinate(e11);

		e7.addSubordinate(e12);
		e7.addSubordinate(e13);
		e7.addSubordinate(e14);

		e8.addSubordinate(e15);
		e8.addSubordinate(e16);

		e3.addSubordinate(e6);
		e3.addSubordinate(e17);

		e4.addSubordinate(e18);
		e4.addSubordinate(e19);

		e5.addSubordinate(e20);
		e5.addSubordinate(e21);

		e9.addSubordinate(e22);

		e10.addSubordinate(e23);
		e10.addSubordinate(e24);

		e11.addSubordinate(e25);

		e12.addSubordinate(e26);
		e12.addSubordinate(e27);

		e13.addSubordinate(e28);

		e15.addSubordinate(e29);
		e15.addSubordinate(e30);

		e16.addSubordinate(e31);

		e17.addSubordinate(e32);

		final OrganizationChart chart = new OrganizationChart(ceo);

		add(new JScrollPane(new DrawPanel(chart)), BorderLayout.CENTER);

		setSize(700, 500);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}

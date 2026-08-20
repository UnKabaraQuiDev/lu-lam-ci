
public class Candidate {

	private String name, firstName;
	private int votes = 0;

	public Candidate(String name, String firstName) {
		this.name = name;
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getVotes() {
		return votes;
	}

	public void voteFor() {
		votes++;
	}

	@Override
	public String toString() {
		return leftPadString(Integer.toString(votes), " ", 5) + " : " + name + " " + firstName;
	}
	
	public static String leftPadString(String str, String fill, int desiredLength) {
		return str.length() < desiredLength ? fill.repeat(desiredLength - str.length()) + str : str;
	}

}

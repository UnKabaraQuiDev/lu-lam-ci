
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Party {

	private String name;
	private List<Candidate> candidates = new ArrayList<>();

	public Party(String name) {
		this.name = name;
	}

	public int getTotalVotes() {
		return candidates.stream().mapToInt(Candidate::getVotes).sum();
	}

	public Candidate findByName(String name) {
		return candidates.stream().filter((c) -> c.getName().startsWith(name)).findFirst().orElse(null);
	}

	public boolean voteFor(int i) {
		if (i >= 0 && i < candidates.size()) {
			candidates.get(i).voteFor();
			return true;
		}
		return false;
	}

	public void sortByVotes() {
		Collections.sort(candidates, (a, b) -> -Integer.compare(a.getVotes(), b.getVotes()));
	}

	public String getName() {
		return name;
	}

	public int size() {
		return candidates.size();
	}
	
	public Object[] toArray() {
		return candidates.toArray();
	}

	public boolean add(Candidate e) {
		return candidates.add(e);
	}

	public void clear() {
		candidates.clear();
	}

	public Candidate get(int i) {
		return candidates.get(i);
	}

	public Candidate remove(int i) {
		return candidates.remove(i);
	}

}

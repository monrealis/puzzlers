package eu.vytenis.galeshapley;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Matcher {
	private final int[][] preferencesOfMen;
	private final int[][] preferencesOfWomen;
	private final Set<Integer> menTaken = new HashSet<>();
	private final Set<Integer> womenTaken = new HashSet<>();
	private final List<Pair> result = new ArrayList<>();

	public Matcher(int[][] preferencesOfMen, int[][] preferencesOfWomen) {
		this.preferencesOfMen = preferencesOfMen;
		this.preferencesOfWomen = preferencesOfWomen;
	}

	public List<Pair> match() {
		while (true) {
			try {
				matchNext();
			} catch (AllMenTaken e) {
				return result;
			}
		}
	}

	private void matchNext() throws AllMenTaken {
		int i = nextFreeMan();
		for (int j = 0; j < n(); ++j) {
			int indexOfWoman = preferencesOfMen[i][j];
			if (!womenTaken.contains(indexOfWoman)) {
				addPair(i, indexOfWoman);
				break;
			}
		}
	}

	private int nextFreeMan() throws AllMenTaken {
		for (int i = 0; i < n(); ++i)
			if (!menTaken.contains(i))
				return i;
		throw new AllMenTaken();

	}

	private void addPair(int indexOfMan, int indexOfWoman) {
		result.add(new Pair(indexOfMan, indexOfWoman));
		menTaken.add(indexOfMan);
		womenTaken.add(indexOfWoman);
	}

	private int n() {
		return preferencesOfMen.length;
	}

	private static class AllMenTaken extends Exception {
		private static final long serialVersionUID = 1L;

	}
}
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
	private final Set<Integer>[] proposalsOfMenMade;
	private final List<Pair> result = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public Matcher(int[][] preferencesOfMen, int[][] preferencesOfWomen) {
		this.preferencesOfMen = preferencesOfMen;
		this.preferencesOfWomen = preferencesOfWomen;
		proposalsOfMenMade = new Set[preferencesOfMen.length];
		for (int i = 0; i < proposalsOfMenMade.length; ++i)
			proposalsOfMenMade[i] = new HashSet<>();
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
		int j = getNextWoman(i);
		addPair(i, j);
	}

	private int nextFreeMan() throws AllMenTaken {
		for (int i = 0; i < n(); ++i)
			if (!menTaken.contains(i))
				return i;
		throw new AllMenTaken();
	}

	private int getNextWoman(int indexOfMan) {
		Set<Integer> proposalsMade = proposalsOfMenMade[indexOfMan];
		int[] preferences = preferencesOfMen[indexOfMan];
		for (int i = 0; i < n(); ++i)
			if (!proposalsMade.contains(preferences[i]))
				return preferences[i];
		throw new IllegalStateException();
	}

	private void addPair(int indexOfMan, int indexOfWoman) {
		result.add(new Pair(indexOfMan, indexOfWoman));
		menTaken.add(indexOfMan);
		womenTaken.add(indexOfWoman);
		proposalsOfMenMade[indexOfMan].add(indexOfWoman);
	}

	private int n() {
		return preferencesOfMen.length;
	}

	private static class AllMenTaken extends Exception {
		private static final long serialVersionUID = 1L;

	}
}
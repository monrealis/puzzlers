package eu.vytenis.galeshapley;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Matcher {
	private final int[][] preferencesOfMen;
	private final int[][] preferencesOfWomen;
	private final Set<Integer>[] proposalsOfMenMade;
	private final Map<Integer, Pair> pairsByManIndex = new TreeMap<>();
	private final Map<Integer, Pair> pairsByWomanIndex = new TreeMap<>();

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
				return new ArrayList<>(pairsByManIndex.values());
			}
		}
	}

	private void matchNext() throws AllMenTaken {
		int i = nextFreeMan();
		int j = getNextWoman(i);
		if (!pairsByWomanIndex.containsKey(j))
			addPair(i, j);
		else {
			Pair old = pairsByWomanIndex.get(j);
			int preferenceOfOldMan = findPreferenceOfWoman(old.getFirst(), j);
			int preferenceOfNewMan = findPreferenceOfWoman(i, j);
			boolean oldPreferred = preferenceOfOldMan < preferenceOfNewMan;
			if (!oldPreferred) {
				removePair(old);
				addPair(i, j);
			} else
				addProposal(i, j);
		}
	}

	private int findPreferenceOfWoman(int indexOfMan, int indexOfWoman) {
		for (int i = 0; i < n(); ++i)
			if (preferencesOfWomen[indexOfWoman][i] == indexOfMan)
				return i;
		throw new IllegalStateException();

	}

	private int nextFreeMan() throws AllMenTaken {
		for (int i = 0; i < n(); ++i)
			if (!pairsByManIndex.containsKey(i))
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
		Pair pair = new Pair(indexOfMan, indexOfWoman);
		pairsByManIndex.put(indexOfMan, pair);
		pairsByWomanIndex.put(indexOfWoman, pair);
		addProposal(indexOfMan, indexOfWoman);
	}

	private void removePair(Pair pair) {
		pairsByManIndex.remove(pair.getFirst());
		pairsByWomanIndex.remove(pair.getSecond());
	}

	private void addProposal(int indexOfMan, int indexOfWoman) {
		proposalsOfMenMade[indexOfMan].add(indexOfWoman);
	}

	private int n() {
		return preferencesOfMen.length;
	}

	private static class AllMenTaken extends Exception {
		private static final long serialVersionUID = 1L;

	}
}
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
	private final Map<Integer, Couple> couplesByManIndex = new TreeMap<>();
	private final Map<Integer, Couple> couplesByWomanIndex = new TreeMap<>();

	@SuppressWarnings("unchecked")
	public Matcher(int[][] preferencesOfMen, int[][] preferencesOfWomen) {
		this.preferencesOfMen = preferencesOfMen;
		this.preferencesOfWomen = preferencesOfWomen;
		proposalsOfMenMade = new Set[preferencesOfMen.length];
		for (int i = 0; i < proposalsOfMenMade.length; ++i)
			proposalsOfMenMade[i] = new HashSet<>();
	}

	public List<Couple> match() {
		while (true) {
			try {
				matchNext();
			} catch (AllMenTaken e) {
				return new ArrayList<>(couplesByManIndex.values());
			}
		}
	}

	private void matchNext() throws AllMenTaken {
		int indexOfFreeMan = nextFreeMan();
		int indexOfPreferredWoman = getNextWoman(indexOfFreeMan);
		Couple oldCouple = couplesByWomanIndex.get(indexOfPreferredWoman);
		Couple newCouple = new Couple(indexOfFreeMan, indexOfPreferredWoman);
		if (oldCouple == null) {
			addProposal(indexOfFreeMan, indexOfPreferredWoman);
			addMarriage(newCouple);
		} else {
			int priorityOfOldMan = findPreferenceOfWoman(oldCouple.getIndexOfMan(), indexOfPreferredWoman);
			int priorityOfNewMan = findPreferenceOfWoman(indexOfFreeMan, indexOfPreferredWoman);
			boolean oldCouplePreferred = priorityOfOldMan < priorityOfNewMan;
			if (!oldCouplePreferred) {
				removeMarriage(oldCouple);
				addMarriage(newCouple);
			} else
				addProposal(indexOfFreeMan, indexOfPreferredWoman);
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
			if (!couplesByManIndex.containsKey(i))
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

	private void addMarriage(Couple couple) {
		couplesByManIndex.put(couple.getIndexOfMan(), couple);
		couplesByWomanIndex.put(couple.getIndexOfWoman(), couple);
	}

	private void removeMarriage(Couple couple) {
		couplesByManIndex.remove(couple.getIndexOfMan());
		couplesByWomanIndex.remove(couple.getIndexOfWoman());
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
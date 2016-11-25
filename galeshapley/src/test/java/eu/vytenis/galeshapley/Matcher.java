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
	private final Map<Integer, Set<Integer>> proposalsOfMenMade = new TreeMap<>();
	private final Map<Integer, Couple> couplesByManIndex = new TreeMap<>();
	private final Map<Integer, Couple> couplesByWomanIndex = new TreeMap<>();

	public Matcher(int[][] preferencesOfMen, int[][] preferencesOfWomen) {
		this.preferencesOfMen = preferencesOfMen;
		this.preferencesOfWomen = preferencesOfWomen;
		for (int i = 0; i < n(); ++i)
			proposalsOfMenMade.put(i, new HashSet<>());
	}

	public List<Couple> match() {
		while (true)
			try {
				matchNext();
			} catch (AllMenTaken e) {
				return new ArrayList<>(couplesByManIndex.values());
			}
	}

	private void matchNext() throws AllMenTaken {
		int indexOfFreeMan = getNextFreeMan();
		int indexOfPreferredWoman = getNextWoman(indexOfFreeMan);
		Couple oldCouple = couplesByWomanIndex.get(indexOfPreferredWoman);
		Couple newCouple = new Couple(indexOfFreeMan, indexOfPreferredWoman);
		if (oldCouple == null) {
			addProposal(newCouple);
			addMarriage(newCouple);
		} else if (isNewManPreferredByWoman(oldCouple, newCouple)) {
			removeMarriage(oldCouple);
			addMarriage(newCouple);
		} else
			addProposal(newCouple);
	}

	private boolean isNewManPreferredByWoman(Couple oldCouple, Couple newCouple) {
		int priorityOfOldMan = findPreferenceOfWoman(oldCouple);
		int priorityOfNewMan = findPreferenceOfWoman(newCouple);
		return priorityOfOldMan > priorityOfNewMan;
	}

	private int findPreferenceOfWoman(Couple couple) {
		for (int i = 0; i < n(); ++i)
			if (preferencesOfWomen[couple.getIndexOfWoman()][i] == couple.getIndexOfMan())
				return i;
		throw new IllegalStateException();
	}

	private int getNextFreeMan() throws AllMenTaken {
		for (int i = 0; i < n(); ++i)
			if (!couplesByManIndex.containsKey(i))
				return i;
		throw new AllMenTaken();
	}

	private int getNextWoman(int indexOfMan) {
		Set<Integer> proposalsMade = proposalsOfMenMade.get(indexOfMan);
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

	private void addProposal(Couple couple) {
		proposalsOfMenMade.get(couple.getIndexOfMan()).add(couple.getIndexOfWoman());
	}

	private int n() {
		return preferencesOfMen.length;
	}

	private static class AllMenTaken extends Exception {
		private static final long serialVersionUID = 1L;
	}
}
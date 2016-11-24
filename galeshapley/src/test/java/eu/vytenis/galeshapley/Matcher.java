package eu.vytenis.galeshapley;

import java.util.ArrayList;
import java.util.List;

public class Matcher {
	private final int[][] preferencesOfMen;
	private final int[][] preferencesOfWomen;
	private final List<Integer> womanTaken = new ArrayList<>();
	private final List<Pair> result = new ArrayList<>();

	public Matcher(int[][] preferencesOfMen, int[][] preferencesOfWomen) {
		this.preferencesOfMen = preferencesOfMen;
		this.preferencesOfWomen = preferencesOfWomen;
	}

	public List<Pair> match() {
		for (int i = 0; i < n(); ++i) {
			for (int j = 0; j < n(); ++j) {
				int indexOfWoman = preferencesOfMen[i][j];
				if (!womanTaken.contains(indexOfWoman)) {
					addPair(i, indexOfWoman);
					break;
				}
			}
		}
		return result;
	}

	private void addPair(int indexOfMan, int indexOfWoman) {
		result.add(new Pair(indexOfMan, indexOfWoman));
		womanTaken.add(indexOfWoman);
	}

	private int n() {
		return preferencesOfMen.length;
	}
}
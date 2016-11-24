package eu.vytenis.galeshapley;

import java.util.ArrayList;
import java.util.List;

public class Matcher {
	private final int[][] preferencesOfMen;
	private final int[][] preferencesOfWomen;

	public Matcher(int[][] preferencesOfMen, int[][] preferencesOfWomen) {
		this.preferencesOfMen = preferencesOfMen;
		this.preferencesOfWomen = preferencesOfWomen;
	}

	public List<Pair> match() {
		List<Pair> result = new ArrayList<>();
		List<Integer> womanTaken = new ArrayList<>();
		for (int i = 0; i < preferencesOfMen.length; ++i) {
			for (int j = 0; j < preferencesOfMen.length; ++j) {
				int indexOfWoman = preferencesOfMen[i][j];
				if (!womanTaken.contains(indexOfWoman)) {
					result.add(new Pair(i, indexOfWoman));
					womanTaken.add(indexOfWoman);
					break;
				}
			}
		}
		return result;
	}
}
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
		for (int i = 0; i < preferencesOfMen.length; ++i) {
			result.add(new Pair(i, preferencesOfMen[i][0]));
		}
		return result;
	}
}
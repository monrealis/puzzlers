package eu.vytenis.galeshapley;

import java.util.ArrayList;
import java.util.List;

public class Matcher {
	private int[][] preferencesOfMen;
	private int[][] preferencesOfWomen;

	public Matcher(int[][] preferencesOfMen, int[][] preferencesOfWomen) {
		this.preferencesOfMen = preferencesOfMen;
		this.preferencesOfWomen = preferencesOfWomen;
	}

	public List<Pair> match() {
		List<Pair> result = new ArrayList<>();
		for (int i = 0; i < preferencesOfMen.length; ++i)
			result.add(new Pair(preferencesOfMen[i][0], preferencesOfWomen[i][0]));
		return result;
	}
}
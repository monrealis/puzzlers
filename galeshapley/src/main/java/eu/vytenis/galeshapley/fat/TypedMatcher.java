package eu.vytenis.galeshapley.fat;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import eu.vytenis.galeshapley.slim.Matcher;

public class TypedMatcher<M, W> {
	private final Woman[][] preferencesOfMen;
	private final Man[][] preferencesOfWomen;

	public TypedMatcher(Woman[][] preferencesOfMen, Man[][] preferencesOfWomen) {
		this.preferencesOfMen = preferencesOfMen;
		this.preferencesOfWomen = preferencesOfWomen;
	}

	public List<TypedCouple<M, W>> match() {
		new Matcher(getPreferencesOfMenIndexes(), getPreferencesOfWomenIndexes()).match();
		return new ArrayList<>();
	}

	private int[][] getPreferencesOfWomenIndexes() {
		int[][] r = new int[0][];
		return r;
	}

	private int[][] getPreferencesOfMenIndexes() {
		int[][] r = new int[0][];
		return r;
	}

	private List<Man> getMen() {
		return asList(preferencesOfWomen[0]);

	}

	private List<Woman> getWomen() {
		return asList(preferencesOfMen[0]);
	}
}

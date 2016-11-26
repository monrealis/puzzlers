package eu.vytenis.galeshapley.fat;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.List;

import eu.vytenis.galeshapley.slim.Couple;
import eu.vytenis.galeshapley.slim.Matcher;

public class TypedMatcher<M, W> {
	private final W[][] preferencesOfMen;
	private final M[][] preferencesOfWomen;

	public TypedMatcher(W[][] preferencesOfMen, M[][] preferencesOfWomen) {
		this.preferencesOfMen = preferencesOfMen;
		this.preferencesOfWomen = preferencesOfWomen;
	}

	public List<TypedCouple<M, W>> match() {
		Matcher m = new Matcher(getPreferencesOfMenIndexes(), getPreferencesOfWomenIndexes());
		List<Couple> couples = m.match();
		List<TypedCouple<M, W>> r = new ArrayList<>();
		for (Couple c : couples)
			r.add(toTypedCouple(c));
		return r;
	}

	private TypedCouple<M, W> toTypedCouple(Couple c) {
		M man = getMen().get(0);
		W woman = getWomen().get(0);
		return new TypedCouple<M, W>(man, woman);
	}

	private int[][] getPreferencesOfMenIndexes() {
		int[][] r = new int[n()][];
		for (int i = 0; i < n(); ++i)
			r[i] = getPreferencesOfMan(i);
		return r;
	}

	private int[][] getPreferencesOfWomenIndexes() {
		int[][] r = new int[n()][];
		for (int i = 0; i < n(); ++i)
			r[i] = getPreferencesOfWoman(i);
		return r;
	}

	private int[] getPreferencesOfMan(int manIndex) {
		int[] r = new int[n()];
		for (int i = 0; i < n(); ++i)
			r[i] = getWomen().indexOf(preferencesOfMen[manIndex][i]);
		return r;
	}

	private int[] getPreferencesOfWoman(int womanIndex) {
		int[] r = new int[n()];
		for (int i = 0; i < n(); ++i)
			r[i] = getMen().indexOf(preferencesOfMen[womanIndex][i]);
		return r;
	}

	private int n() {
		return preferencesOfMen.length;
	}

	private List<M> getMen() {
		return asList(preferencesOfWomen[0]);

	}

	private List<W> getWomen() {
		return asList(preferencesOfMen[0]);
	}
}

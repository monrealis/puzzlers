package eu.vytenis.galeshapley.random;

import static java.util.Collections.shuffle;

import java.util.ArrayList;
import java.util.List;

public class Generator {
	private int numberOfMenAndWomen;

	public Generator(int numberOfMenAndWomen) {
		this.numberOfMenAndWomen = numberOfMenAndWomen;
	}

	public int[][] next() {
		int[][] r = new int[numberOfMenAndWomen][];
		for (int i = 0; i < numberOfMenAndWomen; ++i) {
			r[i] = getRandomRow();
		}
		return r;
	}

	private int[] getRandomRow() {
		List<Integer> r = new ArrayList<>(numberOfMenAndWomen);
		for (int i = 0; i < numberOfMenAndWomen; ++i)
			r.add(i);
		shuffle(r);
		return toArray(r);
	}

	private int[] toArray(List<Integer> integers) {
		int[] row = new int[integers.size()];
		for (int i = 0; i < row.length; ++i)
			row[i] = integers.get(i);
		return row;
	}

}

package eu.vytenis.galeshapley;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

//https://www.youtube.com/watch?v=pc5WSJkFk24
//https://en.wikipedia.org/wiki/Stable_marriage_problem
public class GaleShapleyTest {
	private int[][] preferencesOfMen;
	private int[][] preferencesOfWomen;
	private List<Pair> result;

	@Test
	public void sizeZero() {
		preferencesOfMen = new int[][] {};
		preferencesOfWomen = new int[][] {};
		match();
		assertEquals(asList(), result);
	}

	@Test
	public void sizeOne() {
		preferencesOfMen = new int[][] { { 0 } };
		preferencesOfWomen = new int[][] { { 0 } };
		match();
		assertEquals(asList(new Pair(0, 0)), result);
	}

	private void match() {
		result = new ArrayList<>();
		for (int i = 0; i < preferencesOfMen.length; ++i)
			result.add(new Pair(preferencesOfMen[i][0], preferencesOfWomen[i][0]));
	}
}

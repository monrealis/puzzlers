package eu.vytenis.galeshapley;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Joiner;

//https://www.youtube.com/watch?v=pc5WSJkFk24
//https://en.wikipedia.org/wiki/Stable_marriage_problem
public class GaleShapleyTest {
	private int[][] preferencesOfMen;
	private int[][] preferencesOfWomen;
	private List<Couple> result;

	@Test
	public void sizeZero() {
		preferencesOfMen = new int[][] {};
		preferencesOfWomen = new int[][] {};
		match();
		assertEquals("", getResultString());
	}

	@Test
	public void sizeOne() {
		preferencesOfMen = new int[][] { { 0 } };
		preferencesOfWomen = new int[][] { { 0 } };
		match();
		assertEquals("0,0", getResultString());
	}

	@Test
	public void sizeTwo() {
		preferencesOfMen = new int[][] { { 0, 1 }, { 1, 0 } };
		preferencesOfWomen = new int[][] { { 0, 1 }, { 1, 0 } };
		match();
		assertEquals("0,0 1,1", getResultString());
	}

	@Test
	public void sizeTwo_v2() {
		preferencesOfMen = new int[][] { { 1, 0 }, { 0, 1 } };
		preferencesOfWomen = new int[][] { { 1, 0 }, { 0, 1 } };
		match();
		assertEquals("0,1 1,0", getResultString());
	}

	@Test
	public void sizeTwo_v3() {
		preferencesOfMen = new int[][] { { 0, 1 }, { 0, 1 } };
		preferencesOfWomen = new int[][] { { 0, 1 }, { 0, 1 } };
		match();
		assertEquals("0,0 1,1", getResultString());
	}

	@Test
	public void sizeTwo_v4() {
		preferencesOfMen = new int[][] { { 0, 1 }, { 0, 1 } };
		preferencesOfWomen = new int[][] { { 1, 0 }, { 1, 0 } };
		match();
		assertEquals("0,1 1,0", getResultString());
	}

	private String getResultString() {
		List<String> parts = result.stream().map(p -> p.join(",")).collect(toList());
		return Joiner.on(" ").join(parts);
	}

	private void match() {
		result = new Matcher(preferencesOfMen, preferencesOfWomen).match();
	}
}

package eu.vytenis.galeshapley.random;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import eu.vytenis.galeshapley.slim.Couple;
import eu.vytenis.galeshapley.slim.Matcher;

public class GeneratorTest {

	@Test
	public void size0() {
		generateAndCheckBounds(0);
	}

	@Test
	public void size1() {
		int[][] matrix = generateAndCheckBounds(1);
		assertEquals(0, matrix[0][0]);
	}

	@Test
	public void size10() {
		generateAndCheckBounds(10);
	}

	@Test
	public void size100() {
		generateAndCheckBounds(100);
	}

	@Test
	public void match10() {
		match(10);
	}

	@Test
	public void match100() {
		match(100);
	}

	@Test
	public void match1000() {
		match(1000);
	}

	private void match(int n) {
		int[][] preferencesOfMen = generateAndCheckBounds(n);
		int[][] preferencesOfWomen = generateAndCheckBounds(n);
		List<Couple> couples = new Matcher(preferencesOfMen, preferencesOfWomen).match();
		assertEquals(n, couples.size());
	}

	private int[][] generateAndCheckBounds(int n) {
		int[][] matrix = new Generator(n).next();
		assertEquals(n, matrix.length);
		if (n > 0)
			assertEquals(n, matrix[0].length);
		return matrix;
	}
}

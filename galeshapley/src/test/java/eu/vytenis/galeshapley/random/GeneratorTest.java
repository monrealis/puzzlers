package eu.vytenis.galeshapley.random;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GeneratorTest {
	private int[][] matrix;

	@Test
	public void size0() {
		generateAndCheckBounds(0);
	}

	@Test
	public void size1() {
		generateAndCheckBounds(1);
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

	private void generateAndCheckBounds(int n) {
		matrix = new Generator(n).next();
		assertEquals(n, matrix.length);
		if (n > 0)
			assertEquals(n, matrix[0].length);
	}
}

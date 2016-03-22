package eu.vytenis.problems._20160323;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PolynomialsTest {
	private int a = 10;
	private int b = 11;
	private int c = 12;
	private int[] coefficients = { c, b, a };

	@Test
	public void calculates1() {
		assertEquals(74, calculate1(2));
	}

	@Test
	public void calculates2() {
		assertEquals(74, calculate2(2));
	}

	@Test
	public void calculates3() {
		assertEquals(74, calculate3(2));
	}

	public int calculate1(int x) {
		int r = 0;
		for (int i = 0; i < coefficients.length; ++i)
			r += coefficients[i] * pow(x, i);
		return r;
	}

	private int calculate2(int x) {
		int r = 0;
		int pow = 1;
		for (int i = 0; i < coefficients.length; ++i) {
			r += coefficients[i] * pow;
			pow *= x;
		}
		return r;
	}

	// Calculates using algorithm given in "Code Complete" chapter 29.4
	// ("Expressions")
	private int calculate3(int x) {
		int value = 0;
		for (int i = coefficients.length - 1; i > 0; --i)
			value = (value + coefficients[i]) * x;
		value += coefficients[0];
		return value;
	}

	private int pow(int x, int n) {
		int r = 1;
		for (int i = 1; i <= n; ++i)
			r *= x;
		return r;
	}
}

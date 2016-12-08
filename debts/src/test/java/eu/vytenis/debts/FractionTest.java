package eu.vytenis.debts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class FractionTest {
	private Fraction one = new Fraction(1);
	private Fraction oneHalf = new Fraction(1, 2);
	private Fraction oneThird = new Fraction(1, 3);
	private Fraction oneSixth = new Fraction(1, 6);

	@Test
	public void formatsWholeFraction() {
		assertFraction("1", one);
	}

	@Test
	public void formatsFraction() {
		assertFraction("1 / 2", oneHalf);
	}

	@Test(expected = IllegalArgumentException.class)
	public void doesNotAllowZeroAsDenominator() {
		new Fraction(1, 0);
	}

	@Test
	public void createsZeroFraction() {
		assertFraction("0", new Fraction(0, 5));
	}

	@Test
	public void createsNegativeFraction() {
		assertFraction("-1 / 2", new Fraction(-1, 2));
		assertFraction("-1 / 2", new Fraction(1, -2));
	}

	@Test
	public void createsPositiveFraction() {
		assertFraction("1 / 2", new Fraction(-1, -2));
	}

	@Test
	public void simplifiesFraction() {
		assertFraction("1 / 2", new Fraction(8, 16));
		assertFraction("8 / 9", new Fraction(80, 90));
		assertFraction("9 / 8", new Fraction(90, 80));
	}

	@Test
	public void remembersNominator() {
		assertEquals(1, one.getNumerator());
		assertEquals(1, oneHalf.getNumerator());
	}

	@Test
	public void remembersDenominator() {
		assertEquals(1, one.getDenominator());
		assertEquals(2, oneHalf.getDenominator());
	}

	@Test
	public void dividesByInteger() {
		assertFraction("1 / 2", one.divide(2));
	}

	@Test
	public void addsInteger() {
		assertFraction("3 / 2", oneHalf.add(1));
	}

	@Test
	public void addsFraction() {
		assertFraction("5 / 6", oneHalf.add(oneThird));
	}

	@Test
	public void addsFractionAndSimplifies() {
		assertFraction("1 / 3", oneSixth.add(oneSixth));
	}

	@Test
	public void subtractsFraction() {
		assertFraction("1 / 6", oneHalf.subtract(oneThird));
	}

	@Test
	public void comparesFractions() {
		assertEquals(0, one.compareTo(one));
		assertEquals(1, one.compareTo(oneHalf));
		assertEquals(-1, oneHalf.compareTo(one));
	}

	@Test
	public void equalFractions_equals() {
		assertEquals(one, new Fraction(5 / 5));
		assertEquals(one.hashCode(), new Fraction(5 / 5).hashCode());
	}

	@Test
	public void differentFractions_notEqual() {
		assertNotEquals(one, oneHalf);
		assertNotEquals(one, null);
	}

	private void assertFraction(String expected, Fraction fraction) {
		assertEquals(expected, fraction.toString());
	}
}

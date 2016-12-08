package eu.vytenis.debts;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ProperFractionFormatTest {
	private ProperFractionFormat format = new ProperFractionFormat();

	@Test
	public void formatsLessThanOne() {
		assertEquals("1 / 2", format.format(new Fraction(1, 2)));
	}

	@Test
	public void formatsMoreThanOne() {
		assertEquals("1 1 / 2", format.format(new Fraction(3, 2)));
	}

	@Test
	public void formatsInteger() {
		assertEquals("2", format.format(new Fraction(4, 2)));
		assertEquals("1", format.format(new Fraction(4, 4)));
	}
}

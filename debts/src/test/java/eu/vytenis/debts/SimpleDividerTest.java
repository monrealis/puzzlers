package eu.vytenis.debts;

import static org.junit.Assert.assertEquals;

import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.ProperFractionFormat;
import org.junit.Test;

// http://mindyourdecisions.com/blog/2008/06/10/how-game-theory-solved-a-religious-mystery/
// https://www.youtube.com/watch?v=f4dA4BTv7KQ
// http://www.biu.ac.il/soc/ec/jlwecon/wp/2.%20AumannGame%20-%20bulletin.pdf
public class SimpleDividerTest {
	private ProperFractionFormat f = new ProperFractionFormat();
	private Fraction estate;
	private Fraction debt1 = Fraction.ZERO.add(100);
	private Fraction debt2 = Fraction.ZERO.add(300);
	private Fraction payment1;
	private Fraction payment2;

	@Test
	public void enoughForAll() {
		estate = new Fraction(500, 1);
		split();
		assertPayments("100, 300");
	}

	@Test
	public void example1() {
		estate = new Fraction(2, 3).add(66);
		split();
		assertPayments("33 1 / 3, 33 1 / 3");
	}

	@Test
	public void example2() {
		estate = new Fraction(125, 1);
		split();
		assertPayments("50, 75");
	}

	@Test
	public void example3() {
		estate = new Fraction(200);
		split();
		assertPayments("50, 150");
	}

	private void assertPayments(String expectedPayments) {
		String actual = String.format("%s, %s", format(payment1),
				format(payment2));
		assertEquals(expectedPayments, actual);
	}

	private String format(Fraction fraction) {
		if (fraction.getNumerator() % fraction.getDenominator() == 0)
			return fraction.toString();
		return f.format(fraction);
	}

	private void split() {
		if (debt1.add(debt2).compareTo(estate) <= 0)
			repayAll();
		else
			repayPart();
	}

	private void repayAll() {
		payment1 = debt1;
		payment2 = debt2;
	}

	private void repayPart() {
		Fraction shared = min(debt1, debt2, estate);
		Fraction sharedPart = shared.divide(Fraction.TWO);
		Fraction estateLeft = estate.subtract(shared);
		Fraction additionalPart1 = debt1.compareTo(shared) > 0 ? estateLeft
				: Fraction.ZERO;
		Fraction additionalPart2 = debt2.compareTo(shared) > 0 ? estateLeft
				: Fraction.ZERO;
		payment1 = sharedPart.add(additionalPart1);
		payment2 = sharedPart.add(additionalPart2);
	}

	private Fraction min(Fraction... fractions) {
		Fraction min = fractions[0];
		for (int i = 1; i < fractions.length; ++i)
			if (fractions[i].compareTo(min) < 0)
				min = fractions[i];
		return min;
	}
}

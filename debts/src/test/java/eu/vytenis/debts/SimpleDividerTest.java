package eu.vytenis.debts;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.commons.math3.fraction.Fraction;
import org.apache.commons.math3.fraction.ProperFractionFormat;
import org.junit.Test;

import com.google.common.base.Joiner;

// http://mindyourdecisions.com/blog/2008/06/10/how-game-theory-solved-a-religious-mystery/
// https://www.youtube.com/watch?v=f4dA4BTv7KQ
// http://www.biu.ac.il/soc/ec/jlwecon/wp/2.%20AumannGame%20-%20bulletin.pdf
public class SimpleDividerTest {
	private ProperFractionFormat format = new ProperFractionFormat();
	private Fraction estate;
	private Fraction[] debts = { Fraction.ZERO.add(100), Fraction.ZERO.add(300) };
	private Fraction[] payments;

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
		List<String> s = stream(payments).map(this::format).collect(toList());
		String actual = Joiner.on(", ").join(s);
		assertEquals(expectedPayments, actual);
	}

	private String format(Fraction fraction) {
		if (fraction.getNumerator() % fraction.getDenominator() == 0)
			return fraction.toString();
		return format.format(fraction);
	}

	private void split() {
		if (sum(debts).compareTo(estate) <= 0)
			repayAll();
		else
			repayPart();
	}

	private void repayAll() {
		payments = debts;
	}

	private void repayPart() {
		Fraction shared = min(min(debts), estate);
		Fraction lowerPart = shared.divide(debts.length);
		Fraction estateLeft = estate.subtract(shared);
		Fraction[] upperParts = new Fraction[debts.length];
		for (int i = 0; i < debts.length; ++i)
			upperParts[i] = debts[i].compareTo(shared) > 0 ? estateLeft
					: Fraction.ZERO;
		payments = new Fraction[debts.length];
		for (int i = 0; i < debts.length; ++i)
			payments[i] = lowerPart.add(upperParts[i]);
	}

	private Fraction min(Fraction... fractions) {
		Fraction min = fractions[0];
		for (int i = 1; i < fractions.length; ++i)
			if (fractions[i].compareTo(min) < 0)
				min = fractions[i];
		return min;
	}

	private Fraction sum(Fraction... fractions) {
		Fraction sum = fractions[0];
		for (int i = 1; i < fractions.length; ++i)
			sum = sum.add(fractions[i]);
		return sum;
	}
}

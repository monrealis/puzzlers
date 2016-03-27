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
public class SplitterTest {
	private ProperFractionFormat format = new ProperFractionFormat();
	private Fraction estate;
	private Fraction[] debts = { fraction(100), fraction(300) };
	private Fraction[] payments;

	@Test
	public void enoughForAll() {
		estate = fraction(500);
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

	@Test
	public void tableRow1() {
		debts = fractions(100, 200, 300);
		estate = new Fraction(50, 1);
		split();
		assertPayments("16 2 / 3, 16 2 / 3, 16 2 / 3");
	}

	private Fraction fraction(int sum) {
		return Fraction.ZERO.add(sum);
	}

	private Fraction[] fractions(int... sums) {
		Fraction[] r = new Fraction[sums.length];
		for (int i = 0; i < sums.length; ++i)
			r[i] = new Fraction(sums[i] / 1);
		return r;
	}

	private void split() {
		payments = new Splitter(estate, debts).split();
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

}

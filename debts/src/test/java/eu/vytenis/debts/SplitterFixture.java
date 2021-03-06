package eu.vytenis.debts;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import com.google.common.base.Joiner;

public abstract class SplitterFixture {
	protected ProperFractionFormat format = new ProperFractionFormat();
	protected Fraction estate;
	protected Fraction[] claims;
	protected Fraction[] payments;

	protected Fraction fraction(int sum) {
		return Fraction.ZERO.add(sum);
	}

	protected Fraction[] fractions(int... sums) {
		Fraction[] r = new Fraction[sums.length];
		for (int i = 0; i < sums.length; ++i)
			r[i] = new Fraction(sums[i] / 1);
		return r;
	}

	protected void splitAssert(String expectedPayments) {
		split();
		assertPayments(expectedPayments);
	}

	protected void split() {
		payments = new Splitter(estate, claims).split();
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

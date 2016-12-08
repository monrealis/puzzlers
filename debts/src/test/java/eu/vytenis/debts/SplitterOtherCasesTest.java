package eu.vytenis.debts;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SplitterOtherCasesTest extends SplitterFixture {
	@Test
	public void splits500For3() {
		estate = fraction(500);
		claims = fractions(200, 200, 200);
		splitAssert("166 2 / 3, 166 2 / 3, 166 2 / 3");
	}

	@Test
	public void splits500For4() {
		estate = fraction(500);
		claims = fractions(200, 300, 300, 350);
		split();
		assertPaymentsAreInAscendingOrder();
		assertAllEstateDivided();
	}

	private void assertPaymentsAreInAscendingOrder() {
		for (int i = 0; i < payments.length - 1; ++i)
			assertTrue(payments[i].compareTo(payments[i + 1]) <= 0);
	}

	private void assertAllEstateDivided() {
		assertEquals(estate, getSumOfPayments());
	}

	private Fraction getSumOfPayments() {
		Fraction sum = Fraction.ZERO;
		for (Fraction p : payments)
			sum = sum.add(p);
		return sum;
	}
}

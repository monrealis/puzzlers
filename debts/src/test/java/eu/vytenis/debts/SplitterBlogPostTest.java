package eu.vytenis.debts;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Test;

public class SplitterBlogPostTest extends SplitterFixture {
	public SplitterBlogPostTest() {
		claims = fractions(100, 300);
	}

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
}

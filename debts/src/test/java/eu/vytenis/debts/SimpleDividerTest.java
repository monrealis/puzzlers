package eu.vytenis.debts;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

// http://mindyourdecisions.com/blog/2008/06/10/how-game-theory-solved-a-religious-mystery/
// https://www.youtube.com/watch?v=f4dA4BTv7KQ
// http://www.biu.ac.il/soc/ec/jlwecon/wp/2.%20AumannGame%20-%20bulletin.pdf
public class SimpleDividerTest {
	private Number estate;
	private Number debt1 = new BigDecimal(100);
	private Number debt2 = new BigDecimal(300);
	private Number payment1;
	private Number payment2;

	@Test
	public void enoughForAll() {
		estate = new BigDecimal("5000");
		split();
		assertEquals("100", payment1.toString());
		assertEquals("300", payment2.toString());
	}

	@Test
	public void example1() {
		estate = new BigDecimal("66.66");
		split();
		assertEquals("33.33", payment1.toString());
		assertEquals("33.33", payment2.toString());
	}

	@Test
	public void example2() {
		estate = 125;
		split();
		assertEquals("50", payment1.toString());
		assertEquals("75", payment2.toString());
	}

	private void split() {
		if (debt1().add(debt2()).compareTo(estate()) <= 0)
			repayAll();
		else
			repayPart();
	}

	private void repayAll() {
		payment1 = debt1();
		payment2 = debt2();
	}

	private void repayPart() {
		BigDecimal shared = debt1().min(debt2()).min(estate());
		BigDecimal sharedPart = shared.divide(new BigDecimal(2));
		BigDecimal estateLeft = estate().subtract(shared);
		BigDecimal additionalPart1 = debt1().compareTo(shared) > 0 ? estateLeft
				: BigDecimal.ZERO;
		BigDecimal additionalPart2 = debt2().compareTo(shared) > 0 ? estateLeft
				: BigDecimal.ZERO;
		payment1 = sharedPart.add(additionalPart1);
		payment2 = sharedPart.add(additionalPart2);
	}

	private BigDecimal estate() {
		return new BigDecimal(estate.toString());
	}

	private BigDecimal debt1() {
		return new BigDecimal(debt1.toString());
	}

	private BigDecimal debt2() {
		return new BigDecimal(debt2.toString());
	}
}

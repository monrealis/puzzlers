package eu.vytenis.debts;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

// http://mindyourdecisions.com/blog/2008/06/10/how-game-theory-solved-a-religious-mystery/
// https://www.youtube.com/watch?v=f4dA4BTv7KQ
// http://www.biu.ac.il/soc/ec/jlwecon/wp/2.%20AumannGame%20-%20bulletin.pdf
public class SimpleDividerTest {
	private Number estate = new BigDecimal("66.66");
	private Number debt = new BigDecimal(100);
	private Number debt2 = new BigDecimal(300);
	private Number payment1;
	private Number payment2;

	@Test
	public void example1() {
		split();
		assertEquals(payment1.toString(), "33.33");
		assertEquals(payment2.toString(), "33.33");
	}

	private void split() {
		payment1 = estate().divide(new BigDecimal(2));
		payment2 = estate().divide(new BigDecimal(2));
	}

	private BigDecimal estate() {
		return new BigDecimal(estate.toString());
	}
}

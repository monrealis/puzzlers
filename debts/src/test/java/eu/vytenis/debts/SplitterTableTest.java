package eu.vytenis.debts;

import org.apache.commons.math3.fraction.Fraction;
import org.junit.Test;

public class SplitterTableTest extends BaseSplitterTest {
	public SplitterTableTest() {
		debts = fractions(100, 200, 300);
	}

	@Test
	public void row1() {
		estate = new Fraction(50, 1);
		splitAssert("16 2 / 3, 16 2 / 3, 16 2 / 3");
	}
}

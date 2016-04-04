package eu.vytenis.debts;

import org.junit.Test;

public class SplitterArticleTableTest extends SplitterFixture {
	public SplitterArticleTableTest() {
		claims = fractions(100, 200, 300);
	}

	@Test
	public void row50() {
		estate = fraction(50);
		splitAssert("16 2 / 3, 16 2 / 3, 16 2 / 3");
	}

	@Test
	public void row100() {
		estate = fraction(100);
		splitAssert("33 1 / 3, 33 1 / 3, 33 1 / 3");
	}

	@Test
	public void row150() {
		estate = fraction(150);
		splitAssert("50, 50, 50");
	}

	@Test
	public void row200() {
		estate = fraction(200);
		splitAssert("50, 75, 75");
	}

	@Test
	public void row250() {
		estate = fraction(250);
		splitAssert("50, 100, 100");
	}

	@Test
	public void row300() {
		estate = fraction(300);
		splitAssert("50, 100, 150");
	}

	@Test
	public void row350() {
		estate = fraction(350);
		splitAssert("50, 100, 200");
	}

	@Test
	public void row400() {
		estate = fraction(400);
		splitAssert("50, 125, 225");
	}

	@Test
	public void row450() {
		estate = fraction(450);
		splitAssert("50, 150, 250");
	}

	@Test
	public void row500() {
		estate = fraction(500);
		splitAssert("66 2 / 3, 166 2 / 3, 266 2 / 3");
	}

	@Test
	public void row550() {
		estate = fraction(550);
		splitAssert("83 1 / 3, 183 1 / 3, 283 1 / 3");
	}

	@Test
	public void row600() {
		estate = fraction(600);
		splitAssert("100, 200, 300");
	}
}

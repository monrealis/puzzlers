package eu.vytenis.problems._20170320;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

public class PiTest {
	private static Random random = new Random();
	private int n = 100 * 1000 * 1000;

	@Test
	public void findPi() {
		assertEquals(3.14, getPi(), 0.1);
		System.out.println(getPi());
	}

	private double getPi() {
		return getPercentageInCircle() * 4;
	}

	private double getPercentageInCircle() {
		int in = getInsideCircleCount();
		return 1.0 * in / n;
	}

	private int getInsideCircleCount() {
		int in = 0;
		for (int i = 0; i < n; ++i)
			if (isNextInsideCircle())
				++in;
		return in;
	}

	private boolean isNextInsideCircle() {
		double radius = 0.5;
		double x = random.nextDouble() - radius;
		double y = random.nextDouble() - radius;
		double distanceFromCenter = Math.sqrt(x * x + y * y);
		return distanceFromCenter < radius;
	}
}

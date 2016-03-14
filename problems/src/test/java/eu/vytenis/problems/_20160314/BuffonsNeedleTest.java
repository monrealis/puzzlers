package eu.vytenis.problems._20160314;

import java.util.Random;

import org.junit.Test;

// https://www.youtube.com/watch?v=szUH1rzwbAw
public class BuffonsNeedleTest {
	private int distanceBetweenLines = 2;
	private double pi = Math.PI;
	private double halfNeedleLength = 0.5;
	private int numberOfAttempts = 1000000;
	private Random random = new Random();

	@Test
	public void simulate() {
		int crosses = 0;
		for (int i = 0; i < numberOfAttempts; ++i)
			if (crosses())
				++crosses;
		System.out.println(1.0 * crosses / numberOfAttempts);
		System.out.println(1 / Math.PI);
	}

	private boolean crosses() {
		double centerY = distanceBetweenLines * random.nextDouble();
		double angleRadians = pi * random.nextDouble();
		double maxY = centerY + halfNeedleLength * Math.sin(angleRadians);
		double minY = centerY + halfNeedleLength * Math.sin(pi + angleRadians);
		if (minY <= 0)
			return true;
		if (maxY >= distanceBetweenLines)
			return true;
		return false;
	}
}

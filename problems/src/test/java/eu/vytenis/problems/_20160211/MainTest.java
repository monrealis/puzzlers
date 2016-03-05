package eu.vytenis.problems._20160211;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MainTest {
	@Test
	public void findsAllValidCombinations() {
		assertEquals(136, new MainTest().countValidCombinations());
	}
	
	public int countValidCombinations() {
		List<String> allCombinationsWithUniqueDigits = generateCombinations();
		checkNumberOfCombinations(allCombinationsWithUniqueDigits);
		List<String> validCombinations = collectValidCombinations(allCombinationsWithUniqueDigits);
		checkValidCombinations(validCombinations);
		return validCombinations.size();
	}

	private static List<String> generateCombinations() {
		Generator g = new Generator();
		return g.generate();
	}

	private static void checkNumberOfCombinations(List<String> combinations) {
		if (combinations.size() != 362880)
			throw new RuntimeException();
	}

	private static List<String> collectValidCombinations(
			List<String> allCombinations) {
		return new Tester(allCombinations).collectValid();
	}

	private static void checkValidCombinations(List<String> valid) {
		if (!valid.contains("948567132"))
			throw new RuntimeException();
	}

	private static class Generator {
		private final List<String> combinations = new ArrayList<>();

		public List<String> generate() {
			collectOrLenghten("");
			return combinations;
		}

		private void collectOrLenghten(String combination) {
			if (combination.length() == 9)
				combinations.add(combination);
			else
				lenghten(combination);
		}

		private void lenghten(String digits) {
			for (int i = 1; i <= 9; ++i)
				lenghten(digits, String.valueOf(i));
		}

		private void lenghten(String digits, String newDigit) {
			if (!digits.contains(newDigit))
				collectOrLenghten(digits + newDigit);
		}
	}

	private static class Tester {
		private final List<String> combinations;
		private final List<String> pass = new ArrayList<String>();

		public Tester(List<String> combinations) {
			this.combinations = combinations;
		}

		public List<String> collectValid() {
			for (String digits : combinations)
				if (isValid(digits))
					pass.add(digits);
			return pass;
		}

		private boolean isValid(String digits) {
			double[] d = new double[10]; // digits at positions, d[0] unused
			for (int i = 1; i <= 9; ++i)
				d[i] = Double.parseDouble(digits.substring(i - 1, i));
			double value = d[1] + 13 * d[2] / d[3] + d[4] + 12 * d[5] - d[6]
					- 11 + d[7] * d[8] / d[9] - 10 - 66;
			return Math.abs(value) < 0.00001;
		}
	}
}
package eu.vytenis.debts;

import static java.util.Arrays.stream;

import org.apache.commons.math3.fraction.Fraction;

public class Splitter {
	private final Fraction estate;
	private final Fraction[] debts;
	private final int n;
	private Fraction[] payments;

	public Splitter(Fraction estate, Fraction[] debts) {
		this.estate = estate;
		this.debts = debts;
		n = debts.length;
	}

	public Fraction[] split() {
		if (sum(debts).compareTo(estate) <= 0)
			repayAll();
		else
			repayPart();
		return payments;
	}

	private void repayAll() {
		payments = debts;
	}

	private void repayPart() {
		Fraction shared = min(min(debts), estate);
		Fraction lowerPart = shared.divide(n);
		Fraction estateLeft = estate.subtract(shared);
		Fraction[] upperParts = new Fraction[n];
		for (int i = 0; i < n; ++i)
			upperParts[i] = debts[i].compareTo(shared) > 0 ? estateLeft
					: Fraction.ZERO;
		payments = new Fraction[n];
		for (int i = 0; i < n; ++i)
			payments[i] = lowerPart.add(upperParts[i]);
	}

	private Fraction min(Fraction... fractions) {
		return stream(fractions).reduce(Splitter::minOfTwo).get();
	}

	private Fraction sum(Fraction... fractions) {
		return stream(fractions).reduce(Fraction.ZERO, Splitter::sumOfTwo);
	}

	private static Fraction minOfTwo(Fraction f1, Fraction f2) {
		if (f1.compareTo(f2) <= 0)
			return f1;
		else
			return f2;
	}

	private static Fraction sumOfTwo(Fraction f1, Fraction f2) {
		return f1.add(f2);
	}
}

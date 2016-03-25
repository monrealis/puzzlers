package eu.vytenis.debts;

import org.apache.commons.math3.fraction.Fraction;

public class Splitter {
	private final Fraction estate;
	private final Fraction[] debts;
	private Fraction[] payments;

	public Splitter(Fraction estate, Fraction[] debts) {
		this.estate = estate;
		this.debts = debts;
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
		Fraction lowerPart = shared.divide(debts.length);
		Fraction estateLeft = estate.subtract(shared);
		Fraction[] upperParts = new Fraction[debts.length];
		for (int i = 0; i < debts.length; ++i)
			upperParts[i] = debts[i].compareTo(shared) > 0 ? estateLeft
					: Fraction.ZERO;
		payments = new Fraction[debts.length];
		for (int i = 0; i < debts.length; ++i)
			payments[i] = lowerPart.add(upperParts[i]);
	}

	private Fraction min(Fraction... fractions) {
		Fraction min = fractions[0];
		for (int i = 1; i < fractions.length; ++i)
			if (fractions[i].compareTo(min) < 0)
				min = fractions[i];
		return min;
	}

	private Fraction sum(Fraction... fractions) {
		Fraction sum = fractions[0];
		for (int i = 1; i < fractions.length; ++i)
			sum = sum.add(fractions[i]);
		return sum;
	}
}

package eu.vytenis.debts;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;
import static java.util.Arrays.stream;
import static java.util.Collections.sort;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.math3.fraction.Fraction;

public class Splitter {
	private Fraction estate;
	private final Fraction[] originalDebts;
	private final Fraction[] debts;
	private final Fraction[] payments;
	private final int n;
	private final List<Integer> indexesFromSmallestDebt;
	private final List<Integer> indexesFromGreatestDebt;

	public Splitter(Fraction estate, Fraction[] debts) {
		this.estate = estate;
		this.debts = copyOf(debts, debts.length);
		this.originalDebts = copyOf(debts, debts.length);
		this.n = debts.length;
		this.payments = new Fraction[n];
		fill(payments, Fraction.ZERO);
		ByDebtComparator c = new ByDebtComparator();
		indexesFromSmallestDebt = orderDebtors(c);
		indexesFromGreatestDebt = orderDebtors(c.reversed());
	}

	private List<Integer> orderDebtors(Comparator<Integer> comparator) {
		List<Integer> debtorIndexes = new LinkedList<>();
		for (int i = 0; i < n; ++i)
			debtorIndexes.add(i);
		sort(debtorIndexes, comparator);
		return debtorIndexes;
	}

	public Fraction[] split() {
		repayLowerHalf();
		repayUpperHalf();
		return payments;
	}

	private void repayLowerHalf() {
		for (int ii = 0; ii < n; ++ii) {
			int index = indexesFromSmallestDebt.get(ii);
			Fraction half = originalDebts[index].divide(2).subtract(
					payments[index]);
			Fraction remainingForEach = estate.divide(indexesFromSmallestDebt
					.size());
			Fraction min = min(half, remainingForEach);
			for (int j = ii; j < n; ++j)
				add(j, min);
		}
	}

	private void repayUpperHalf() {
		for (int ii = 0; ii < n; ++ii) {
			int index = indexesFromGreatestDebt.get(ii);
			boolean last = ii == n - 1;
			Fraction remaining;
			if (last)
				remaining = originalDebts[index].subtract(payments[index]);
			else {
				int nextIndex = indexesFromGreatestDebt.get(ii + 1);
				remaining = originalDebts[index].subtract(
						originalDebts[nextIndex]).divide(2);

			}
			Fraction remainingForEach = estate.divide(ii + 1);
			Fraction min = min(remaining, remainingForEach);
			for (int j = 0; j <= ii; ++j)
				add(indexesFromGreatestDebt.get(j), min);
		}
	}

	private void add(int i, Fraction amount) {
		payments[i] = payments[i].add(amount);
		estate = estate.subtract(amount);
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

	private final class ByDebtComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer i1, Integer i2) {
			return debts[i1].compareTo(debts[i2]);
		}
	}
}

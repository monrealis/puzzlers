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
	private final Fraction[] debts;
	private final Fraction[] payments;
	private final int n;
	private final List<Integer> payeeIndexes;

	public Splitter(Fraction estate, Fraction[] debts) {
		this.estate = estate;
		this.debts = copyOf(debts, debts.length);
		this.n = debts.length;
		this.payments = new Fraction[n];
		fill(payments, Fraction.ZERO);
		payeeIndexes = getSortedPayeeIndexesFromSmallest();
	}

	private List<Integer> getSortedPayeeIndexesFromSmallest() {
		List<Integer> debtorIndexes = new LinkedList<>();
		for (int i = 0; i < n; ++i)
			debtorIndexes.add(i);
		sort(debtorIndexes, new ByDebtComparator());
		return debtorIndexes;
	}

	public Fraction[] split() {
		repayLowerHalf();
		repayUpperHalf();
		return payments;
	}

	private void repayLowerHalf() {
		for (int ii = 0; ii < n; ++ii) {
			new LowerPartSplitter(ii).split();
		}
	}

	private class LowerPartSplitter {
		private final int ii;

		public LowerPartSplitter(int ii) {
			this.ii = ii;
		}

		public void split() {
			int index = payeeIndexes.get(ii);
			Fraction half = debts[index].divide(2).subtract(payments[index]);
			Fraction remainingForEach = estate.divide(n - ii);
			Fraction min = min(half, remainingForEach);
			for (int j = ii; j < n; ++j)
				add(j, min);
		}

	}

	private void repayUpperHalf() {
		for (int ii = n - 1; ii >= 0; --ii) {
			new UpperPartSplitter(ii).split();
		}
	}

	private class UpperPartSplitter {
		private final int ii;

		public UpperPartSplitter(int ii) {
			this.ii = ii;
		}

		public void split() {
			int index = payeeIndexes.get(ii);
			boolean last = ii == 0;
			Fraction remaining;
			if (last)
				remaining = debts[index].subtract(payments[index]);
			else {
				int nextIndex = payeeIndexes.get(ii - 1);
				remaining = debts[index].subtract(debts[nextIndex]).divide(2);

			}
			Fraction remainingForEach = estate.divide(n - ii);
			Fraction min = min(remaining, remainingForEach);
			for (int j = ii; j < n; ++j)
				add(payeeIndexes.get(j), min);
		}
	}

	private void add(int i, Fraction amount) {
		payments[i] = payments[i].add(amount);
		estate = estate.subtract(amount);
	}

	private Fraction min(Fraction... fractions) {
		return stream(fractions).reduce(Splitter::minOfTwo).get();
	}

	private static Fraction minOfTwo(Fraction f1, Fraction f2) {
		if (f1.compareTo(f2) <= 0)
			return f1;
		else
			return f2;
	}

	private final class ByDebtComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer i1, Integer i2) {
			return debts[i1].compareTo(debts[i2]);
		}
	}
}

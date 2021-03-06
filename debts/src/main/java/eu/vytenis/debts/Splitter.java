package eu.vytenis.debts;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;
import static java.util.Collections.sort;

import java.util.LinkedList;
import java.util.List;

//http://mindyourdecisions.com/blog/2008/06/10/how-game-theory-solved-a-religious-mystery/
//https://www.youtube.com/watch?v=f4dA4BTv7KQ
//http://www.biu.ac.il/soc/ec/jlwecon/wp/2.%20AumannGame%20-%20bulletin.pdf
public class Splitter {
	private Fraction estate;
	private final Fraction[] claims;
	private final Fraction[] payments;
	private final List<Integer> sortedPayeeIndexes;

	public Splitter(Fraction estate, Fraction[] claims) {
		this.estate = estate;
		this.claims = copyOf(claims, claims.length);
		this.payments = new Fraction[getClaimCount()];
		fill(payments, Fraction.ZERO);
		sortedPayeeIndexes = getSortedPayeeIndexesFromSmallest();
	}

	private List<Integer> getSortedPayeeIndexesFromSmallest() {
		List<Integer> payeeIndexes = new LinkedList<>();
		for (int i = 0; i < getClaimCount(); ++i)
			payeeIndexes.add(i);
		sort(payeeIndexes, new PayeeIndexByClaimComparator(claims));
		return payeeIndexes;
	}

	public Fraction[] split() {
		payLowerHalf();
		payUpperHalf();
		return payments;
	}

	private void payLowerHalf() {
		for (int i = 0; i < getClaimCount(); ++i)
			new LowerPartPayer(i).execute();
	}

	private void payUpperHalf() {
		for (int i = getClaimCount() - 1; i >= 0; --i)
			new UpperPartPayer(i).execute();
	}

	private void pay(int index, Fraction amount) {
		payments[index] = payments[index].add(amount);
		estate = estate.subtract(amount);
	}

	private int getClaimCount() {
		return claims.length;
	}

	private class LowerPartPayer {
		private final int sortedPayeeIndex;

		public LowerPartPayer(int sortedPayeeIndex) {
			this.sortedPayeeIndex = sortedPayeeIndex;
		}

		public void execute() {
			payForEach(getSumToPay());
		}

		private Fraction getSumToPay() {
			Fraction sum = getSumToPayInThisIteration();
			Fraction remainingSum = getMaxSumToPayRemainingForEachPerson();
			return Fractions.min(sum, remainingSum);
		}

		private Fraction getSumToPayInThisIteration() {
			Fraction half = claims[index()].divide(2).subtract(payments[index()]);
			return half;
		}

		private int index() {
			return sortedPayeeIndexes.get(sortedPayeeIndex);
		}

		private Fraction getMaxSumToPayRemainingForEachPerson() {
			return estate.divide(getClaimCount() - sortedPayeeIndex);
		}

		private void payForEach(Fraction sumToPay) {
			for (int j = sortedPayeeIndex; j < getClaimCount(); ++j)
				pay(j, sumToPay);
		}
	}

	private class UpperPartPayer {
		private final int sortedPayeeIndex;

		public UpperPartPayer(int sortedPayeeIndex) {
			this.sortedPayeeIndex = sortedPayeeIndex;
		}

		public void execute() {
			payForEach(getSumToPay());
		}

		private Fraction getSumToPay() {
			Fraction sum = getSumToPayInThisIteration();
			Fraction remainingSum = getMaxSumToPayRemainingForEachPerson();
			return Fractions.min(sum, remainingSum);
		}

		private void payForEach(Fraction payedSum) {
			for (int j = sortedPayeeIndex; j < getClaimCount(); ++j)
				pay(sortedPayeeIndexes.get(j), payedSum);
		}

		private Fraction getSumToPayInThisIteration() {
			if (isLast())
				return claims[index()].subtract(payments[index()]);
			else
				return claims[index()].subtract(claims[nextIndex()]).divide(2);
		}

		private Fraction getMaxSumToPayRemainingForEachPerson() {
			return estate.divide(getClaimCount() - sortedPayeeIndex);
		}

		private Integer index() {
			return sortedPayeeIndexes.get(sortedPayeeIndex);
		}

		private Integer nextIndex() {
			return sortedPayeeIndexes.get(sortedPayeeIndex - 1);
		}

		private boolean isLast() {
			return sortedPayeeIndex == 0;
		}
	}
}

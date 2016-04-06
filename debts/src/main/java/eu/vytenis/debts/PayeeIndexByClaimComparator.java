package eu.vytenis.debts;

import java.util.Comparator;

import org.apache.commons.math3.fraction.Fraction;

public class PayeeIndexByClaimComparator implements Comparator<Integer> {
	private final Fraction[] claims;

	public PayeeIndexByClaimComparator(Fraction[] claims) {
		this.claims = claims;
	}

	@Override
	public int compare(Integer index1, Integer index2) {
		return claims[index1].compareTo(claims[index2]);
	}
}
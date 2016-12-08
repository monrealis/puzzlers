package eu.vytenis.debts;

public class ProperFractionFormat {
	public String format(Fraction fraction) {
		int n = fraction.getNumerator();
		int d = fraction.getDenominator();
		if (n % d == 0)
			return String.valueOf(n / d);
		if (n / d < 1)
			return fraction.toString();
		String fractionPart = format(fraction.subtract(new Fraction(n / d, 1)));
		return String.format("%s %s", n / d, fractionPart);
	}
}

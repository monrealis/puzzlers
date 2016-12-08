package eu.vytenis.debts;

import static java.util.Arrays.asList;

public class Fraction implements Comparable<Fraction> {
	public static final Fraction ZERO = new Fraction(0);
	private int numerator;
	private int denominator;

	public Fraction(int nominator) {
		this(nominator, 1);
	}

	public Fraction(int numerator, int denominator) {
		if (denominator == 0)
			throw new IllegalArgumentException("Infinity not supported");
		int gcd = getGcd(Math.abs(numerator), Math.abs(denominator));
		if (denominator < 0) {
			this.numerator = -numerator / gcd;
			this.denominator = -denominator / gcd;
		} else {
			this.numerator = numerator / gcd;
			this.denominator = denominator / gcd;
		}
	}

	private int getGcd(int first, int second) {
		if (first == 0)
			return second;
		return getGcd(second % first, first);
	}

	public int getDenominator() {
		return denominator;
	}

	public int getNumerator() {
		return numerator;
	}

	public Fraction divide(int divisor) {
		return new Fraction(numerator, denominator * divisor);
	}

	public Fraction add(int number) {
		return new Fraction(numerator + denominator * number, denominator);
	}

	public Fraction subtract(Fraction amount) {
		return add(amount.negate());
	}

	private Fraction negate() {
		return new Fraction(-numerator, denominator);
	}

	public Fraction add(Fraction amount) {
		int n = numerator * amount.getDenominator() + amount.getNumerator() * denominator;
		int d = denominator * amount.getDenominator();
		return new Fraction(n, d);
	}

	public int compareTo(Fraction other) {
		return value().compareTo(other.value());
	}

	private Double value() {
		return 1.0 * this.numerator / this.denominator;
	}

	@Override
	public String toString() {
		if (denominator == 1)
			return String.valueOf(numerator);
		return String.format("%s / %s", numerator, denominator);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Fraction))
			return false;
		Fraction f = (Fraction) obj;
		if (numerator != f.getNumerator())
			return false;
		return denominator == f.getDenominator();
	}

	@Override
	public int hashCode() {
		return asList(numerator, denominator).hashCode();
	}
}

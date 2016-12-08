package eu.vytenis.debts;

import static java.util.Arrays.stream;

public class Fractions {
	public static Fraction min(Fraction... fractions) {
		return stream(fractions).reduce(Fractions::minOfTwo).get();
	}

	public static Fraction minOfTwo(Fraction f1, Fraction f2) {
		if (f1.compareTo(f2) <= 0)
			return f1;
		else
			return f2;
	}
}

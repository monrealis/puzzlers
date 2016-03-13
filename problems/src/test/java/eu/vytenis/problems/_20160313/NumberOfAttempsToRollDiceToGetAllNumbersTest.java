package eu.vytenis.problems._20160313;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;

import org.junit.Test;

public class NumberOfAttempsToRollDiceToGetAllNumbersTest {
	private List<Integer> rollCounts = new ArrayList<>();
	private Random random = new Random();

	@Test
	public void run() {
		rollManyTimes();
		aggregate();
	}

	private void rollManyTimes() {
		for (int i = 0; i < 10000; ++i)
			rollCounts.add(getNumberOfAttemptsToRollAll());
	}

	private int getNumberOfAttemptsToRollAll() {
		Set<Integer> rolled = new TreeSet<>();
		int n = 0;
		while (true) {
			++n;
			rolled.add(random.nextInt(6));
			if (rolled.size() == 6)
				return n;
		}
	}

	private void aggregate() {
		double avg = 1.0 * reduce(Integer::sum) / rollCounts.size();
		System.out.println(reduce(Integer::min));
		System.out.println(reduce(Integer::max));
		System.out.println(avg);
	}

	private Integer reduce(BinaryOperator<Integer> function) {
		return rollCounts.stream().reduce(function).get();
	}
}

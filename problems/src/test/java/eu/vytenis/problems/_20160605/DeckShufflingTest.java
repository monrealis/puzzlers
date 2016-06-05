package eu.vytenis.problems._20160605;

import static java.util.Collections.reverse;
import static java.util.Collections.shuffle;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

// https://www.youtube.com/watch?v=oUo53pIplhc
public class DeckShufflingTest {
	private static Random random = new Random();

	@Test
	public void zeroInPlace() {
		List<Integer> deck = generateOrdered();
		reverse(deck);
		assertEquals(0, getNumberOrCardsInPlace(deck));
	}

	@Test
	public void oneInPlace() {
		List<Integer> deck = new ArrayList<>();
		for (int i = 0; i < 52; ++i)
			deck.add(0);
		assertEquals(1, getNumberOrCardsInPlace(deck));
	}

	@Test
	public void allCardsInPlace() {
		assertEquals(52, getNumberOrCardsInPlace(generateOrdered()));
	}

	@Test
	public void simulate() {
		double avg = getAverageNumberOfCardsInPlace();
		assertTrue(String.valueOf(avg), avg > 0.99);
		assertTrue(String.valueOf(avg), avg < 1.01);
	}

	private double getAverageNumberOfCardsInPlace() {
		int inPlace = 0;
		int numberOfTests = 100000;
		for (int i = 0; i < numberOfTests; ++i)
			inPlace += getNumberOrCardsInPlace(generateShuffled());
		double avg = 1.0 * inPlace / numberOfTests;
		return avg;
	}

	private int getNumberOrCardsInPlace(List<Integer> deck) {
		int r = 0;
		for (int i = 0; i < 52; ++i)
			if (deck.get(i).equals(i))
				++r;
		return r;
	}

	private List<Integer> generateShuffled() {
		List<Integer> r = generateOrdered();
		shuffle(r, random);
		return r;
	}

	private List<Integer> generateOrdered() {
		List<Integer> r = new ArrayList<>();
		for (int i = 0; i < 52; ++i)
			r.add(i);
		return r;
	}

}

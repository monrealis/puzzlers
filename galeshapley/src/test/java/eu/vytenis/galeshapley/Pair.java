package eu.vytenis.galeshapley;

import static java.util.Arrays.asList;

import java.util.List;

public class Pair {
	private int first;
	private int second;

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Pair)
			return toList().equals(((Pair) obj).toList());
		return false;
	}

	@Override
	public int hashCode() {
		return toList().hashCode();
	}

	private List<Integer> toList() {
		return asList(first, second);
	}
	
	public int getFirst() {
		return first;
	}
	
	public int getSecond() {
		return second;
	}
}
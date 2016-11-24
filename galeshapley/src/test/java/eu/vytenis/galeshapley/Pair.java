package eu.vytenis.galeshapley;

import static java.util.Arrays.asList;

import java.util.List;

import com.google.common.base.Joiner;

public class Pair {
	private final int indexOfMan;
	private final int indexOfWoman;

	public Pair(int indexOfMan, int indexOfWoman) {
		this.indexOfMan = indexOfMan;
		this.indexOfWoman = indexOfWoman;
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
		return asList(indexOfMan, indexOfWoman);
	}

	public int getIndexOfMan() {
		return indexOfMan;
	}

	public int getIndexOfWoman() {
		return indexOfWoman;
	}

	@Override
	public String toString() {
		return join(", ");
	}

	public String join(String separator) {
		return Joiner.on(separator).join(indexOfMan, indexOfWoman);
	}
}
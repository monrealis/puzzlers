package eu.vytenis.galeshapley.fat;

import static java.util.Arrays.asList;

import java.util.List;

import com.google.common.base.Joiner;

public class TypedCouple<M, W> {
	private final M man;
	private final W woman;

	public TypedCouple(M man, W woman) {
		this.man = man;
		this.woman = woman;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TypedCouple<?, ?>)
			return toList().equals(((TypedCouple<?, ?>) obj).toList());
		return false;
	}

	@Override
	public int hashCode() {
		return toList().hashCode();
	}

	private List<Object> toList() {
		return asList(man, woman);
	}

	public M getMan() {
		return man;
	}

	public W getWoman() {
		return woman;
	}

	@Override
	public String toString() {
		return join(", ");
	}

	public String join(String separator) {
		return Joiner.on(separator).join(man, woman);
	}
}
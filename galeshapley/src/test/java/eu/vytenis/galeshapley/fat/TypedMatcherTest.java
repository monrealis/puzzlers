package eu.vytenis.galeshapley.fat;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Joiner;

public class TypedMatcherTest {
	private List<TypedCouple<Man, Woman>> couples;
	private Woman[] preferencesOfMen;
	private Man[][] preferencesOfWomen;

	@Test
	public void size0() {
		match();
		assertEquals("", getResultString());
	}

	private void match() {
		couples = new TypedMatcher<Man, Woman>().match();
	}

	private String getResultString() {
		List<String> parts = couples.stream().map(p -> p.join(",")).collect(toList());
		return Joiner.on(" ").join(parts);
	}

	public enum Man {
		John

	}

	public enum Woman {
		Sally

	}
}

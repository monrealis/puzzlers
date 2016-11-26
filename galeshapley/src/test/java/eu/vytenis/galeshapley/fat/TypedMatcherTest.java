package eu.vytenis.galeshapley.fat;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;
import static eu.vytenis.galeshapley.fat.Man.John;
import static eu.vytenis.galeshapley.fat.Woman.Sally;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.google.common.base.Joiner;

public class TypedMatcherTest {
	private List<TypedCouple<Man, Woman>> couples;
	private Woman[][] preferencesOfMen;
	private Man[][] preferencesOfWomen;

	@Test
	public void size0() {
		match();
		assertEquals("", getResultString());
	}

	@Test
	@Ignore
	public void size1() {
		preferencesOfMen = new Woman[][] { { Sally } };
		preferencesOfWomen = new Man[][] { { John } };
		match();
		assertEquals("John,Sally", getResultString());
	}

	private void match() {
		couples = new TypedMatcher<Man, Woman>(preferencesOfMen, preferencesOfWomen).match();
	}

	private String getResultString() {
		List<String> parts = couples.stream().map(p -> p.join(",")).collect(toList());
		return Joiner.on(" ").join(parts);
	}
}

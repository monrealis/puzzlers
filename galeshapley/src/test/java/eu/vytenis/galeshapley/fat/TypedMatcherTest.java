package eu.vytenis.galeshapley.fat;

import static eu.vytenis.galeshapley.fat.Man.*;
import static eu.vytenis.galeshapley.fat.Woman.*;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

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
		preferencesOfMen = new Woman[][] {};
		preferencesOfWomen = new Man[][] {};
		match();
		assertEquals("", getResultString());
	}

	@Test
	public void size1() {
		preferencesOfMen = new Woman[][] { { Rhea } };
		preferencesOfWomen = new Man[][] { { Frank } };
		match();
		assertEquals("Frank,Rhea", getResultString());
	}

	@Test
	public void youtube_pc5WSJkFk24() {
		preferencesOfMen = new Woman[][] { //
				{ Kate, Mary, Rhea, Jill }, //
				{ Mary, Jill, Rhea, Kate }, //
				{ Kate, Rhea, Jill, Mary }, //
				{ Rhea, Mary, Kate, Jill }, //
		};
		preferencesOfWomen = new Man[][] { //
				{ Frank, Mac, Dennis, Charlie }, //
				{ Mac, Charlie, Dennis, Frank }, //
				{ Dennis, Mac, Charlie, Frank }, //
				{ Charlie, Dennis, Frank, Mac },//
		};
		match();
		assertEquals("Frank,Rhea Dennis,Jill Mac,Kate Charlie,Mary", getResultString());
	}

	private void match() {
		couples = new TypedMatcher<Man, Woman>(preferencesOfMen, preferencesOfWomen).match();
	}

	private String getResultString() {
		List<String> parts = couples.stream().map(p -> p.join(",")).collect(toList());
		return Joiner.on(" ").join(parts);
	}
}

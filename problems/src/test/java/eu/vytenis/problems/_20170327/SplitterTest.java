package eu.vytenis.problems._20170327;

import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SplitterTest {
	@Test(expected = LengthIsLessThanZero.class)
	public void doesNotAcceptNegativeLength() {
		split("", -1);
	}

	@Test(expected = LengthIsZero.class)
	public void doesNotAcceptZero() {
		split("", 0);
	}

	@Test(expected = TextIsNull.class)
	public void doesNotAcceptNullText() {
		split(null, 1);
	}

	@Test
	public void echoesEmpty() {
		split("", 1);
	}

	@Test
	public void echoesNotEmpty() {
		assertEquals("x", split("x", 1));
	}

	@Test
	public void splitsWordLongerThanLimit() {
		assertEquals("h\ni", split("hi", 1));
		assertEquals("hel\nlo", split("hello", 3));
	}

	@Test
	public void splitsUsingSpace() {
		assertEquals("hi\nthere", split("hi there", 5));
		assertEquals("hi\nthere", split("hi there", 6));
		assertEquals("hi\nthere", split("hi there", 7));
	}

	@Test
	public void skipsSpaces() {
		assertEquals("", split(" ", 5));
		assertEquals("", split("  ", 5));
		assertEquals("", split("   ", 5));
		assertEquals("x", split(" x ", 1));
		assertEquals("x\nx", split(" x    x   ", 1));
	}

	@Test
	public void skipsOftherTypesOfWhitespace() {
		assertEquals("", split("\t", 5));
		assertEquals("", split("\t\n", 5));
		assertEquals("", split("\n\r", 5));
		assertEquals("x", split(" x ", 1));
		assertEquals("x\nx", split(" x \f  x   ", 1));
	}

	@Test
	public void squashesWhitespace() {
		assertEquals("x\nyz", split("x\t \t\nyz", 2));
		assertEquals("x y\nz", split("x\t \t\ny  z", 3));
	}

	@Test
	public void splitsGivenInput() {
		assertEquals("žodis žodis\nžodis", split("žodis žodis žodis", 13));
	}

	public String split(String text, int maxLineLength) {
		return new Splitter(text, maxLineLength).split();
	}

	private static class Splitter {
		private final String text;
		private final int maxLineLength;
		private final List<String> lines = new ArrayList<>();
		private String squashedText;
		private String currentLine = "";

		public Splitter(String text, int maxLineLength) {
			this.text = text;
			this.maxLineLength = maxLineLength;
		}

		public String split() {
			checkInput();
			prepareSquashedInput();
			iterateCharacterByCharacter();
			addNextLineIfNotEmpty();
			return joinLines();
		}

		private void checkInput() {
			if (text == null)
				throw new TextIsNull();
			if (maxLineLength < 0)
				throw new LengthIsLessThanZero();
			if (maxLineLength == 0)
				throw new LengthIsZero();
		}

		private void prepareSquashedInput() {
			squashedText = text.replaceAll("(?s)\\s+", " ");
		}

		private void iterateCharacterByCharacter() {
			for (int i = 0; i < squashedText.length(); ++i) {
				currentLine = currentLine + squashedText.charAt(i);
				handleCharacterAppendedToLine(isNextWordBreak(i));
			}
		}

		private boolean isNextWordBreak(int characterIndex) {
			if (characterIndex == squashedText.length() - 1)
				return true;
			return squashedText.charAt(characterIndex + 1) == ' ';
		}

		private void handleCharacterAppendedToLine(boolean nextIsWordBreak) {
			if (currentLineTrimmed().length() < maxLineLength)
				return;
			if (nextIsWordBreak)
				addWholeLine();
			else if (isLastLineOneWord())
				addWholeLine();
			else
				addPartOfLine();
		}

		private void addPartOfLine() {
			int index = currentLineTrimmed().lastIndexOf(' ');
			String beforeLastSpace = currentLineTrimmed().substring(0, index);
			String afterLastSpace = currentLineTrimmed().substring(index + 1);
			lines.add(beforeLastSpace);
			currentLine = afterLastSpace;
		}

		private boolean isLastLineOneWord() {
			return currentLineTrimmed().indexOf(' ') < 0;
		}

		private void addNextLineIfNotEmpty() {
			if (currentLineTrimmed().isEmpty())
				return;
			addWholeLine();
		}

		private void addWholeLine() {
			lines.add(currentLineTrimmed());
			currentLine = "";
		}

		private String currentLineTrimmed() {
			return currentLine.trim();
		}

		private String joinLines() {
			return lines.stream().collect(joining("\n"));
		}
	}

	private static class TextIsNull extends RuntimeException {
		private static final long serialVersionUID = 1;
	}

	private static class LengthIsLessThanZero extends RuntimeException {
		private static final long serialVersionUID = 1;
	}

	private static class LengthIsZero extends RuntimeException {
		private static final long serialVersionUID = 1;
	}
}

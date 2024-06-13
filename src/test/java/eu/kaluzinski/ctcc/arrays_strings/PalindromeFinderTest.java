package eu.kaluzinski.ctcc.arrays_strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PalindromeFinderTest {

  @Test
  void shouldReturnTrueWhenStringsArePalindromes() {
    var finder = new PalindromeFinder();
    assertTrue(finder.arePalindromes("abecadło", "ołdaceba"));
  }

  @Test
  void shouldReturnFalseWhenStringAreNotPalindromes() {
    var finder = new PalindromeFinder();
    assertFalse(finder.arePalindromes("abecadło", "ołdcceba"));
  }

  @Test
  void shouldReturnFalseWhenStringHaveDifferentLengths() {
    var finder = new PalindromeFinder();
    assertFalse(finder.arePalindromes("abecadło", "ołdaceba "));
  }
}

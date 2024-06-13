package eu.kaluzinski.ctcc.arrays_strings;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UniqueCharactersFinderTest {

  @Test
  void shouldReturnTrueWhenAllCharactersAreUnique() {
    var finder = new UniqueCharactersFinder();
    assertTrue(finder.hasUnique("hasuniqUe"));
  }

  @Test
  void shouldReturnFalseWhenNotAllCharactersAreUnique() {
    var finder = new UniqueCharactersFinder();
    assertFalse(finder.hasUnique("hasunique"));
  }
}

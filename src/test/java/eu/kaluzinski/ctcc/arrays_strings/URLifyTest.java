package eu.kaluzinski.ctcc.arrays_strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class URLifyTest {

  @Test
  void shouldUrlEncodeSpaces() {
    assertEquals("Mr%20John%20Smith", URLify.urlify("Mr John Smith", 13));
  }
}

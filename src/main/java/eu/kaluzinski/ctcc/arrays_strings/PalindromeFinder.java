package eu.kaluzinski.ctcc.arrays_strings;

import java.util.Arrays;

public class PalindromeFinder {

  boolean arePalindromes(String first, String second) {
    if (first.length() != second.length()) {
      return false;
    }

    var firstSorted = sortText(first);
    var secondSorted = sortText(second);

    return firstSorted.equals(secondSorted);
  }

  private String sortText(String text) {
    var characters = text.toCharArray();
    Arrays.sort(characters);
    return new String(characters);
  }

}

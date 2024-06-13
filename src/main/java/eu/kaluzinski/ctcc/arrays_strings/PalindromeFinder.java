package eu.kaluzinski.ctcc.arrays_strings;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PalindromeFinder {

  boolean arePalindromes(String first, String second) {
    if (first.length() != second.length()) {
      return false;
    }

    var firstSorted = first.toCharArray();
    Arrays.sort(firstSorted);

    var secondSorted = second.toCharArray();
    Arrays.sort(secondSorted);

    return IntStream.range(0, firstSorted.length)
        .noneMatch(i -> firstSorted[i] != secondSorted[i]);
  }

}

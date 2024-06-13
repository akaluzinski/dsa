package eu.kaluzinski.ctcc.arrays_strings;

import java.util.Arrays;

public class URLify {

  public static String urlify(String text, int textLength) {
    var characters = text.toCharArray();
    var spacesCount = countCharacter(characters, 0, textLength, ' ');
    var newSize = textLength + 3 * spacesCount;
    var charactersWithSpace = Arrays.copyOf(characters, newSize);

    return new String(charactersWithSpace);
  }

  private static int countCharacter(char[] textCharacters, int start, int end,
      char searchedCharacter) {
    var count = 0;
    for (int i = start; i < end; ++i) {
      if (textCharacters[i] == searchedCharacter) {
        ++count;
      }
    }
    return count;
  }

}

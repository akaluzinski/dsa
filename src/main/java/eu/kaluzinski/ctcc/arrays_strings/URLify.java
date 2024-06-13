package eu.kaluzinski.ctcc.arrays_strings;

import java.util.Arrays;

public class URLify {

  public String urlify(String text, int textLength) {
    var characters = text.toCharArray();
    var spacesCount = countCharacter(characters, 0, textLength, ' ');
    var newSize = textLength + 2 * spacesCount;
    var charactersWithSpace = Arrays.copyOf(characters, newSize);
    var newIndex = newSize - 1;

    for (int oldIndex = textLength - 1; oldIndex >= 0; --oldIndex) {
      if (charactersWithSpace[oldIndex] == ' ') {
        charactersWithSpace[newIndex] = '0';
        charactersWithSpace[newIndex - 1] = '2';
        charactersWithSpace[newIndex - 2] = '%';
        newIndex -= 3;
      } else {
        charactersWithSpace[newIndex] = charactersWithSpace[oldIndex];
        --newIndex;
      }
    }

    return new String(charactersWithSpace);
  }

  private int countCharacter(char[] textCharacters, int start, int end, char searchedChar) {
    var count = 0;
    for (int i = start; i < end; ++i) {
      if (textCharacters[i] == searchedChar) {
        ++count;
      }
    }
    return count;
  }

}

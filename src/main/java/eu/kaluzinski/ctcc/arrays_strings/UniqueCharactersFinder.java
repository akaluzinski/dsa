package eu.kaluzinski.ctcc.arrays_strings;

public class UniqueCharactersFinder {

  private final int charactersSetSize = 128;

  public boolean hasUnique(String text) {
    if (text.length() > charactersSetSize) {
      return false;
    }

    var charactersFound = new boolean[charactersSetSize];
    for (int i = 0; i < text.length(); ++i) {
      var character = text.charAt(i);
      if (charactersFound[character]) {
        return false;
      }
      charactersFound[character] = true;
    }

    return true;
  }


}

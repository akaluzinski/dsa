package eu.kaluzinski.ctcc.arrays_strings;

public class UniqueCharactersFinder {

  private final int charactersSetSize = 128;

    public boolean hasUnique(String text) {
        if (text.length() > charactersSetSize) return false;
        var seen = new boolean[charactersSetSize];
        for (var c : text.toCharArray()) {
            if (seen[c]) return false;
            seen[c] = true;
        }
        return true;
    }


}

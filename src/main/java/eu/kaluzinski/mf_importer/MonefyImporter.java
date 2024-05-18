package eu.kaluzinski.mf_importer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class MonefyImporter {

  public static final String UTF8_BOM = "\uFEFF";
  private static final String DELIMITER = ";";

  public List<List<String>> rawImportMonefyFile(String path) {
    try {
      var records = Files.readAllLines(Paths.get(path))
          .stream()
          .map(line -> Arrays.asList(line.split(DELIMITER)))
          .toList();

      return sanitizeMonefyImportData(records);
    } catch (IOException e) {
      throw new RuntimeException("Import of %s failed.".formatted(path), e);
    }
  }

  private List<List<String>> sanitizeMonefyImportData(List<List<String>> rawImportData) {
    if (rawImportData.isEmpty()) {
      return rawImportData;
    }

    var header = rawImportData.get(0);
    if (!header.isEmpty()) {
      var firstHeader = header.get(0);
      header.set(0, removeUTF8BOM(firstHeader));
    }
    return rawImportData;
  }

  private String removeUTF8BOM(String s) {
    return s.startsWith(UTF8_BOM) ? s.substring(1) : s;
  }

}

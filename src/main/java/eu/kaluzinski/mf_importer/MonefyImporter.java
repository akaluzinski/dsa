package eu.kaluzinski.mf_importer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MonefyImporter {

  public static final String UTF8_BOM = "\uFEFF";
  private static final String DELIMITER = ";";

  public List<List<String>> rawImportMonefyFile(String path) {
    try {
      var records = Files.readAllLines(Paths.get(path))
          .stream()
          .map(line -> Arrays.asList(line.split(DELIMITER)))
          .collect(Collectors.toList());

      return normalizeMonefyImportData(records);
    } catch (IOException e) {
      throw new RuntimeException("Import of %name failed.".formatted(path), e);
    }
  }

  private List<List<String>> normalizeMonefyImportData(List<List<String>> rawImportData) {
    if (rawImportData.isEmpty()) {
      return rawImportData;
    }

    var headers = rawImportData.get(0);
    if (!headers.isEmpty()) {
      var firstHeader = headers.get(0);
      headers.set(0, removeUTF8BOM(firstHeader));
    }

    if (rawImportData.size() == 1) {
      return rawImportData;
    }

    return rawImportData.stream().map(dataRow -> {
      var hasDescriptionCellMissing = dataRow.size() == headers.size() - 1;
      if (!hasDescriptionCellMissing) {
        return dataRow;
      }
      var normalizedRow = new LinkedList<>(dataRow);
      normalizedRow.add("");
      return normalizedRow;
    }).toList();
  }

  private String removeUTF8BOM(String s) {
    return s.startsWith(UTF8_BOM) ? s.substring(1) : s;
  }

}

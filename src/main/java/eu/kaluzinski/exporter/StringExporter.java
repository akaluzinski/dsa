package eu.kaluzinski.exporter;

import eu.kaluzinski.mf_importer.converters.JsonConverter;
import eu.kaluzinski.mf_importer.reports.Insights;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class StringExporter {

  public void exportInsights(Insights insights, Path path) {
    var stringifiedInsights = new JsonConverter().toJson(insights);
    try {
      Files.writeString(path, stringifiedInsights);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}

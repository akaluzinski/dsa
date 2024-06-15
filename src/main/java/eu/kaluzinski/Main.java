package eu.kaluzinski;

import static eu.kaluzinski.mf_importer.flow.FlowsProvider.basicFlow;

import eu.kaluzinski.exporter.StringExporter;
import java.nio.file.Path;

public class Main {

  public static void main(String[] args) {
    var insights = basicFlow().importAndGenerate("src/main/resources/import.csv", "mBank");
    new StringExporter().exportInsights(insights, Path.of("exports.json"));
  }
}

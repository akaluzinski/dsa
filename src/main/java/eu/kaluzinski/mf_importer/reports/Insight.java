package eu.kaluzinski.mf_importer.reports;

import eu.kaluzinski.mf_importer.emums.Metric;

public record Insight(Metric metric, Object value) {

  public static Insight of(Metric metric, Object value) {
    return new Insight(metric, value);
  }
}

package eu.kaluzinski.mf_importer.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.kaluzinski.mf_importer.reports.Insights;

public class JsonConverter {


  public String toJson(Insights insights) {
    var mapper = new ObjectMapper();

    try {
      return mapper
          .writerWithDefaultPrettyPrinter()
          .writeValueAsString(insights);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }


}

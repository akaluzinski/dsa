package eu.kaluzinski.controller;

import static eu.kaluzinski.mf_importer.flow.FlowsProvider.basicFlow;

import eu.kaluzinski.mf_importer.converters.JsonConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InsightsController {

  @GetMapping("/insights")
  public String sayHello() {
    var insights = basicFlow().importAndGenerate("src/main/resources/import.csv", "mBank");
    var stringifiedInsights = new JsonConverter().toJson(insights);

    return stringifiedInsights;
  }

}

package eu.kaluzinski.controller;

import static eu.kaluzinski.mf_importer.flow.FlowsProvider.basicFlow;

import eu.kaluzinski.mf_importer.converters.JsonConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(
    origins = {
        "http://localhost:3000"
    },
    methods = {
        RequestMethod.OPTIONS,
        RequestMethod.GET,
        RequestMethod.PUT,
        RequestMethod.DELETE,
        RequestMethod.POST
    })
@RestController
public class InsightsController {

  @GetMapping(value = "/insights", produces = "application/json")
  public String sayHello() {
    var insights = basicFlow().importAndGenerate("src/main/resources/import.csv", "mBank");
    var stringifiedInsights = new JsonConverter().toJson(insights);

    return stringifiedInsights;
  }

}

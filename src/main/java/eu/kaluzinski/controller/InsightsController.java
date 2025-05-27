package eu.kaluzinski.controller;

import static eu.kaluzinski.mf_importer.flow.FlowsProvider.basicFlow;
import static eu.kaluzinski.mf_importer.flow.FlowsProvider.categoryFlow;

import eu.kaluzinski.mf_importer.converters.JsonConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

  private static final String ALL = "all";

  @GetMapping(value = "/insights", produces = "application/json")
  public String insights(@RequestParam(value = "category", defaultValue = ALL) String category) {
    var flow = category.equals(ALL) ? basicFlow() : categoryFlow(category);
    return new JsonConverter().toJson(
        flow.importAndGenerate("src/main/resources/import.csv", "mBank")
    );
  }

}
